#!/bin/bash

# 快速上传命令生成器

echo "╔══════════════════════════════════════════════════════════════╗"
echo "║                                                              ║"
echo "║              GitHub 快速上传命令生成器                        ║"
echo "║                                                              ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo ""
echo "请输入您的GitHub信息:"
echo ""
read -p "GitHub用户名: " username
read -p "仓库名 (例如: weather-app): " repo
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "生成的命令如下，请复制执行:"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
cat << EOF
cd /home/you/cursor/weather
git init
git add .
git commit -m "Android Weather App - Initial commit"
git remote add origin https://github.com/$username/$repo.git
git branch -M main
git push -u origin main
EOF
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
read -p "是否立即执行这些命令? (y/n): " execute

if [ "$execute" = "y" ]; then
    echo ""
    echo "开始执行..."
    echo ""
    cd /home/you/cursor/weather
    git init
    git add .
    git commit -m "Android Weather App - Initial commit"
    git remote add origin "https://github.com/$username/$repo.git"
    git branch -M main
    git push -u origin main
    
    if [ $? -eq 0 ]; then
        echo ""
        echo "✓ 上传成功！"
        echo ""
        echo "下一步:"
        echo "1. 访问: https://github.com/$username/$repo"
        echo "2. 点击 'Actions' 标签查看编译进度"
        echo ""
    else
        echo ""
        echo "✗ 上传失败，请检查:"
        echo "1. 仓库是否已创建: https://github.com/new"
        echo "2. 网络连接是否正常"
        echo "3. GitHub凭据是否正确"
        echo ""
    fi
else
    echo "已取消执行，您可以手动复制上面的命令执行。"
fi
