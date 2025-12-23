#!/bin/bash

echo "========================================="
echo "  GitHub上传助手"
echo "========================================="
echo ""

# 检查是否已经是git仓库
if [ -d .git ]; then
    echo "⚠️  检测到已存在的Git仓库"
    read -p "是否继续? (y/n): " confirm
    if [ "$confirm" != "y" ]; then
        exit 0
    fi
else
    echo "✓ 初始化Git仓库..."
    git init
fi

echo ""
echo "请输入您的GitHub仓库信息:"
echo "----------------------------------------"
read -p "GitHub用户名: " username
read -p "仓库名 (例如: weather-app): " repo

REPO_URL="https://github.com/$username/$repo.git"

echo ""
echo "将上传到: $REPO_URL"
read -p "确认继续? (y/n): " confirm

if [ "$confirm" != "y" ]; then
    echo "已取消"
    exit 0
fi

echo ""
echo "========================================="
echo "  开始上传..."
echo "========================================="
echo ""

# 添加所有文件
echo "1/4: 添加文件..."
git add .

# 提交
echo "2/4: 提交代码..."
git commit -m "Android Weather App - Initial commit"

# 设置远程仓库
echo "3/4: 配置远程仓库..."
git remote remove origin 2>/dev/null
git remote add origin "$REPO_URL"

# 推送
echo "4/4: 推送到GitHub..."
git branch -M main
git push -u origin main

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "  ✓ 上传成功！"
    echo "========================================="
    echo ""
    echo "下一步:"
    echo "1. 访问: https://github.com/$username/$repo"
    echo "2. 点击 'Actions' 标签"
    echo "3. 查看自动编译进度"
    echo "4. 编译完成后下载APK"
    echo ""
else
    echo ""
    echo "========================================="
    echo "  ✗ 上传失败"
    echo "========================================="
    echo ""
    echo "可能的原因:"
    echo "1. 仓库不存在 - 请先在GitHub创建仓库"
    echo "2. 没有权限 - 检查用户名和密码"
    echo "3. 网络问题 - 检查网络连接"
    echo ""
    echo "手动上传方法:"
    echo "1. 访问 https://github.com/new 创建仓库"
    echo "2. 在仓库页面点击上传文件"
    echo "3. 将项目打包上传"
    echo ""
fi
