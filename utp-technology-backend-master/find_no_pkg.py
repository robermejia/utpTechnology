import os

root_dir = 'src/main/java'

for root, dirs, files in os.walk(root_dir):
    for file in files:
        if file.endswith('.java'):
            path = os.path.join(root, file)
            with open(path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            if 'package ' not in content:
                print(f"Missing package: {path}")
