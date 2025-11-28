const express = require('express');
const cors = require('cors');
const sqlite3 = require('sqlite3');
const { open } = require('sqlite');
const path = require('path');

require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;
const ADMIN_KEY = process.env.ADMIN_KEY || 'dev-admin-key'; // override in production

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Serve static files from the repo's docs/ folder so that GET / returns the site
const docsPath = path.join(__dirname, '..', 'docs');
app.use(express.static(docsPath));

let db;
(async () => {
  db = await open({
    filename: path.join(__dirname, 'data.db'),
    driver: sqlite3.Database
  });

  await db.run(`CREATE TABLE IF NOT EXISTS signups (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    email TEXT UNIQUE,
    message TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
  )`);
})();

// Simple health check
app.get('/health', (req, res) => {
  res.json({ status: 'ok' });
});

// Public signup endpoint
app.post('/api/signup', async (req, res) => {
  const { name, email, message } = req.body;
  if (!email) return res.status(400).json({ error: 'Email required' });
  try {
    const result = await db.run(
      'INSERT INTO signups (name, email, message) VALUES (?, ?, ?)',
      [name || null, email, message || null]
    );
    res.json({ success: true, id: result.lastID });
  } catch (err) {
    if (err.message && err.message.includes('UNIQUE')) {
      return res.status(409).json({ error: 'Email already registered' });
    }
    console.error(err);
    res.status(500).json({ error: 'Server error' });
  }
});

// Admin middleware: require ADMIN_KEY via header x-admin-key or Bearer token
function requireAdmin(req, res, next) {
  const headerKey = (req.headers['x-admin-key'] || (req.headers.authorization && req.headers.authorization.split(' ')[1]) || null);
  if (!headerKey || headerKey !== ADMIN_KEY) {
    return res.status(401).json({ error: 'Unauthorized' });
  }
  next();
}

// Protected signups listing
app.get('/api/signups', requireAdmin, async (req, res) => {
  try {
    const rows = await db.all('SELECT id, name, email, message, created_at FROM signups ORDER BY created_at DESC');
    res.json(rows);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Server error' });
  }
});

// fallback for SPA routes: serve index.html for any other GET (helps client-side routing)
app.get('*', (req, res) => {
  // If the request is for an API path, skip
  if (req.path.startsWith('/api') || req.path === '/health') return res.status(404).json({ error: 'Not found' });
  res.sendFile(path.join(docsPath, 'index.html'));
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
  console.log(`Admin key: ${ADMIN_KEY ? '[SET]' : '[NOT SET]'} (set ADMIN_KEY env var in production)`);
});
