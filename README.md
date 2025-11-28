# SDET Plan — Local dev for signup feature

This repository contains your interactive site under `docs/` and a small Express backend under `server/` to accept signups and persist them to SQLite.

Quick start (macOS / zsh)

1. Install Node.js (v16+ recommended). Use nvm if needed.

2. Install server dependencies:

```bash
cd server
npm install
```

3. Start the backend locally:

```bash
cd server
npm run start
# or in dev mode with live reload:
# npm run dev
```

By default the server listens on port 3000. It creates `server/data.db` automatically.

4. Open the static site locally (optional):

```bash
# from repository root (serves entire folder via python http server)
python3 -m http.server 8000
# open http://localhost:8000/docs/signup.html in your browser
```

5. Test the signup flow using the page at `http://localhost:8000/docs/signup.html`. It will POST to `http://localhost:3000/api/signup` in development.

6. Verify entries in the backend:

```bash
# from server folder
node -e "(async()=>{const sqlite3=require('sqlite3');const {open}=require('sqlite');const db=await open({filename:'data.db',driver:sqlite3.Database});console.log(await db.all('SELECT * FROM signups'));await db.close();})();"
```

Deploy notes

- When deploying the backend, set `PRODUCTION_API` in `docs/signup.html` to your backend URL (e.g. `https://api.yourdomain.com`).
- Ensure CORS is enabled in the server (it already is) and HTTPS is used in production.
- For GitHub Pages, static site remains in `/docs`; backend must be hosted elsewhere (Heroku, Render, Fly, Vercel, etc.).

Security

- Ensure you validate/sanitize inputs and implement rate-limiting and reCAPTCHA for production to prevent spam.
- For production, store secrets in environment variables and enable HTTPS.


