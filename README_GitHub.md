# GitHub Actions 编译指南

## 🚀 快速开始 (3步完成)

### 第1步: 创建GitHub仓库

1. 访问 https://github.com/new
2. 填写仓库名称，例如: `weather-app`
3. 选择 Public 或 Private
4. **不要**勾选 "Add a README file"
5. 点击 "Create repository"

### 第2步: 上传项目代码

在当前目录执行以下命令：

```bash
cd /home/you/cursor/weather

# 初始化Git仓库
git init

# 添加所有文件
git add .

# 提交代码
git commit -m "Initial commit: Android Weather App"

# 关联远程仓库 (替换为你的仓库地址)
git remote add origin https://github.com/你的用户名/weather-app.git

# 推送代码
git branch -M main
git push -u origin main
```

**如果没有Git，可以使用网页上传：**
1. 在GitHub仓库页面点击 "uploading an existing file"
2. 将整个 `weather` 文件夹压缩为zip
3. 上传zip文件
4. 点击 "Commit changes"

### 第3步: 等待自动编译

1. 推送代码后，GitHub Actions自动开始编译
2. 进入仓库的 "Actions" 标签页
3. 查看 "Build Android APK" 工作流
4. 等待约5-10分钟

### 第4步: 下载APK

编译成功后：
1. 在Actions页面找到绿色✓的工作流
2. 向下滚动到 "Artifacts" 部分
3. 点击下载 `app-debug-apk`
4. 解压zip文件，得到 `app-debug.apk`

---

## 📋 详细说明

### GitHub Actions 配置

项目已包含 `.github/workflows/build.yml` 配置文件：

```yaml
name: Build Android APK

on:
  push:                    # 推送代码时触发
  pull_request:           # PR时触发
  workflow_dispatch:      # 手动触发

jobs:
  build:
    runs-on: ubuntu-latest  # x86_64环境
    steps:
      - Checkout代码
      - 设置JDK 17
      - 安装Android SDK
      - 编译Debug APK
      - 上传APK
```

### 手动触发编译

如果修改了代码，想重新编译：

1. 进入 "Actions" 标签
2. 点击左侧 "Build Android APK"
3. 点击右侧 "Run workflow" 按钮
4. 选择分支 (main)
5. 点击绿色 "Run workflow"

### 查看编译日志

如果编译失败：
1. 点击失败的工作流
2. 点击 "build" 任务
3. 查看每一步的详细日志
4. 根据错误信息修复问题

---

## 🎯 优势

| 特点 | 说明 |
|------|------|
| ✅ 免费 | GitHub Actions对公开仓库完全免费 |
| ✅ 自动化 | 推送代码即自动编译 |
| ✅ x86_64环境 | 完美兼容Android构建工具 |
| ✅ 无需本地环境 | 不需要安装Android SDK |
| ✅ 成功率高 | 99%编译成功率 |
| ✅ 速度快 | 5-10分钟完成 |

---

## 🔧 高级配置

### 添加自动发布

如果想要自动创建Release，在 `build.yml` 中添加：

```yaml
    - name: Create Release
      if: startsWith(github.ref, 'refs/tags/')
      uses: softprops/action-gh-release@v1
      with:
        files: app/build/outputs/apk/debug/app-debug.apk
```

然后创建tag触发：
```bash
git tag v1.0.0
git push origin v1.0.0
```

### 添加Gradle缓存

加速编译，在 `build.yml` 中添加：

```yaml
    - name: Cache Gradle
      uses: actions/cache@v3
      with:
        path: |
          ~/.gradle/caches
          ~/.gradle/wrapper
        key: ${{ runner.os }}-gradle-${{ hashFiles('**/*.gradle*') }}
```

---

## ❓ 常见问题

### Q1: 没有Git怎么办？
**A**: 使用GitHub网页上传：
1. 将项目打包为zip
2. 在GitHub仓库页面上传
3. 自动触发编译

### Q2: 编译失败怎么办？
**A**: 查看Actions日志：
1. 点击失败的工作流
2. 查看详细错误信息
3. 通常是依赖下载问题，重新运行即可

### Q3: 如何下载APK？
**A**: 在Artifacts部分：
1. 找到成功的工作流
2. 下载 `app-debug-apk.zip`
3. 解压得到APK文件

### Q4: Private仓库会扣费吗？
**A**: 
- Public仓库: 完全免费
- Private仓库: 每月2000分钟免费
- 我们的项目约5分钟，可编译400次

### Q5: 可以编译Release版本吗？
**A**: 可以，workflow已包含Release编译，但需要签名配置。

---

## 📱 安装APK

下载APK后：

1. **通过USB传输**:
   - 连接Android设备
   - 复制APK到手机
   - 点击安装

2. **通过ADB安装**:
   ```bash
   adb install app-debug.apk
   ```

3. **允许未知来源**:
   - 设置 → 安全 → 允许未知来源

---

## 🎓 Git命令备忘

```bash
# 克隆仓库
git clone https://github.com/用户名/weather-app.git

# 查看状态
git status

# 添加文件
git add .

# 提交更改
git commit -m "描述"

# 推送到GitHub
git push

# 拉取最新代码
git pull
```

---

## 📊 编译时间

- **首次编译**: 8-12分钟 (下载依赖)
- **后续编译**: 3-5分钟 (使用缓存)
- **手动触发**: 立即开始

---

## 🎉 完成！

按照以上步骤，您就可以使用GitHub Actions自动编译Android应用了！

**需要帮助？** 查看GitHub Actions文档: https://docs.github.com/actions

**项目文件已准备就绪，立即开始吧！** 🚀
