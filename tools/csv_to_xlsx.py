#!/usr/bin/env python3
import csv
import sys
from openpyxl import Workbook

def csv_to_xlsx(csv_path, xlsx_path):
    wb = Workbook()
    ws = wb.active
    with open(csv_path, newline='', encoding='utf-8') as f:
        reader = csv.reader(f)
        for r in reader:
            ws.append(r)
    wb.save(xlsx_path)

if __name__ == '__main__':
    if len(sys.argv) < 3:
        print('Usage: csv_to_xlsx.py input.csv output.xlsx')
        sys.exit(1)
    csv_to_xlsx(sys.argv[1], sys.argv[2])

