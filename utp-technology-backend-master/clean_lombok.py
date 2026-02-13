import os
import re

root_dir = 'src'
lombok_patterns = [
    r'import lombok\..*;',
    r'@Getter',
    r'@Setter',
    r'@ToString',
    r'@EqualsAndHashCode',
    r'@NoArgsConstructor',
    r'@AllArgsConstructor',
    r'@RequiredArgsConstructor',
    r'@Builder',
    r'@Data',
    r'@SneakyThrows',
    r'@Slf4j'
]

for root, dirs, files in os.walk(root_dir):
    for file in files:
        if file.endswith('.java'):
            path = os.path.join(root, file)
            with open(path, 'r', encoding='utf-8') as f:
                lines = f.readlines()
            
            new_lines = []
            changed = False
            for line in lines:
                new_line = line
                for pattern in lombok_patterns:
                    if re.search(pattern, new_line):
                        new_line = re.sub(pattern, '', new_line)
                        changed = True
                new_lines.append(new_line)
            
            if changed:
                print(f"Cleaning {path}")
                with open(path, 'w', encoding='utf-8') as f:
                    f.writelines(new_lines)
