#!/bin/bash

# 打包Android天气预报项目
# 用于传输到其他计算机编译

echo "================================"
echo "打包Android天气预报项目"
echo "================================"
echo ""

PROJECT_DIR="/home/you/cursor/weather"
OUTPUT_DIR="/home/you/cursor"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)

cd "$OUTPUT_DIR"

# 创建tar.gz格式
echo "正在创建 tar.gz 格式压缩包..."
tar -czf "weather-app-${TIMESTAMP}.tar.gz" \
    --exclude='weather/.gradle' \
    --exclude='weather/.idea' \
    --exclude='weather/build' \
    --exclude='weather/app/build' \
    --exclude='weather/local.properties' \
    weather/

if [ $? -eq 0 ]; then
    SIZE=$(du -h "weather-app-${TIMESTAMP}.tar.gz" | cut -f1)
    echo "✓ tar.gz 创建成功: weather-app-${TIMESTAMP}.tar.gz (${SIZE})"
else
    echo "✗ tar.gz 创建失败"
fi

echo ""

# 创建zip格式
echo "正在创建 zip 格式压缩包..."
zip -r "weather-app-${TIMESTAMP}.zip" weather/ \
    -x "weather/.gradle/*" \
    -x "weather/.idea/*" \
    -x "weather/build/*" \
    -x "weather/app/build/*" \
    -x "weather/local.properties" \
    > /dev/null 2>&1

if [ $? -eq 0 ]; then
    SIZE=$(du -h "weather-app-${TIMESTAMP}.zip" | cut -f1)
    echo "✓ zip 创建成功: weather-app-${TIMESTAMP}.zip (${SIZE})"
else
    echo "✗ zip 创建失败"
fi

echo ""
echo "================================"
echo "打包完成！"
echo "================================"
echo ""
echo "压缩包位置: $OUTPUT_DIR"
echo ""
echo "使用方法:"
echo "1. 将压缩包传输到有Android Studio的计算机"
echo "2. 解压: tar -xzf weather-app-*.tar.gz"
echo "   或: unzip weather-app-*.zip"
echo "3. 用Android Studio打开项目"
echo "4. 等待Gradle同步完成"
echo "5. 编译运行"
echo ""
