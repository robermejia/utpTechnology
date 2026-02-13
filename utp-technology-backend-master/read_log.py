import sys

try:
    with open('build_v23.log', 'r', encoding='utf-16le') as f:
        lines = f.readlines()
        for i, line in enumerate(lines):
            if '[ERROR]' in line:
                for j in range(max(0, i-5), i+1):
                    print(lines[j].strip())
                print("---")
except Exception as e:
    print(f"Error: {e}")
