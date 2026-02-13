import sys

try:
    with open('build_v29.log', 'r', encoding='utf-16le') as f:
        lines = f.readlines()
    
    with open('errors_v29.txt', 'w', encoding='utf-8') as f:
        for i, line in enumerate(lines):
            if '[ERROR]' in line and '.java' in line:
                f.write(line)
except Exception as e:
    print(f"Error: {e}")
