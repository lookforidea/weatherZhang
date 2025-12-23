#!/bin/bash

# Android天气预报应用构建脚本
# 用于编译打包生成APK文件

echo "开始构建漳州天气预报应用..."

# 检查Java版本
echo "检查Java版本..."
java -version

# 检查Android SDK路径
if [ -z "$ANDROID_HOME" ]; then
    echo "警告: ANDROID_HOME 环境变量未设置"
    echo "请设置ANDROID_HOME指向Android SDK目录"
fi

# 清理项目
echo "清理项目..."
./gradlew clean

# 构建Debug版本
echo "构建Debug版本APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✓ Debug版本构建成功!"
    echo "APK位置: app/build/outputs/apk/debug/app-debug.apk"
else
    echo "✗ Debug版本构建失败"
    exit 1
fi

# 构建Release版本
echo "构建Release版本APK..."
./gradlew assembleRelease

if [ $? -eq 0 ]; then
    echo "✓ Release版本构建成功!"
    echo "APK位置: app/build/outputs/apk/release/app-release.apk"
else
    echo "✗ Release版本构建失败"
    exit 1
fi

echo "构建完成!"
