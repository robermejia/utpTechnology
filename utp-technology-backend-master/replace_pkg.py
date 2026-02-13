import os

root_dir = 'src'
old_pkg = 'com.utp.technology.http'
new_pkg = 'com.utp.technology.web'

for root, dirs, files in os.walk(root_dir):
    for file in files:
        if file.endswith('.java'):
            path = os.path.join(root, file)
            with open(path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            if old_pkg in content:
                print(f"Updating {path}")
                new_content = content.replace(old_pkg, new_pkg)
                with open(path, 'w', encoding='utf-8') as f:
                    f.write(new_content)
