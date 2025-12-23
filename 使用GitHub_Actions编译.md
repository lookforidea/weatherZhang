# 使用GitHub Actions自动编译APK

## 什么是GitHub Actions?

GitHub Actions是GitHub提供的免费CI/CD服务，可以自动编译Android应用。

**优点**:
- ✅ 完全免费
- ✅ x86_64环境(兼容Android构建工具)
- ✅ 自动化编译
- ✅ 无需本地环境
- ✅ 成功率100%

## 快速开始

### 步骤1: 创建GitHub仓库

1. 访问 https://github.com/new
2. 创建新仓库，例如: `weather-app`
3. 设置为Public或Private

### 步骤2: 上传项目代码

**方法A: 使用Git命令行**

```bash
cd /home/you/cursor/weather

# 初始化git
git init

# 添加所有文件
git add .

# 提交
git commit -m "Initial commit: Android Weather App"

# 关联远程仓库(替换为你的仓库地址)
git remote add origin https://github.com/你的用户名/weather-app.git

# 推送代码
git branch -M main
git push -u origin main
```

**方法B: 使用GitHub网页上传**

1. 在GitHub仓库页面
2. 点击 "Upload files"
3. 拖拽项目文件夹
4. 点击 "Commit changes"

### 步骤3: GitHub Actions自动编译

项目已包含 `.github/workflows/build.yml` 配置文件，推送代码后会自动触发编译。

#### 查看编译进度

1. 进入你的GitHub仓库
2. 点击 "Actions" 标签
3. 查看最新的workflow运行状态

#### 手动触发编译

1. 进入 "Actions" 标签
2. 点击左侧 "Build Android APK"
3. 点击右侧 "Run workflow"
4. 点击绿色 "Run workflow" 按钮

### 步骤4: 下载编译好的APK

编译完成后(约5-10分钟):

1. 在Actions页面找到成功的workflow运行
2. 向下滚动到 "Artifacts" 部分
3. 下载 `app-debug-apk`
4. 解压zip文件，得到 `app-debug.apk`

## Workflow配置说明

当前配置 (`.github/workflows/build.yml`):

```yaml
name: Build Android APK

on:
  push:                    # 代码推送时触发
    branches: [ main, master ]
  pull_request:           # PR时触发
    branches: [ main, master ]  
  workflow_dispatch:      # 手动触发

jobs:
  build:
    runs-on: ubuntu-latest  # 使用Ubuntu x86_64环境
    
    steps:
    - name: Checkout code
      uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
    
    - name: Setup Android SDK
      uses: android-actions/setup-android@v2
    
    - name: Build Debug APK
      run: ./gradlew assembleDebug
    
    - name: Upload Debug APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug-apk
        path: app/build/outputs/apk/debug/app-debug.apk
```

## 触发条件

Actions会在以下情况自动运行:

1. **推送代码到main/master分支**
2. **创建Pull Request**
3. **手动触发** (workflow_dispatch)

## 编译时间

- 首次编译: 约8-12分钟(下载依赖)
- 后续编译: 约3-5分钟(有缓存)

## 限制

GitHub Actions免费版限制:

- **Public仓库**: 无限制
- **Private仓库**: 每月2000分钟免费

我们的项目编译约5分钟，所以:
- Public仓库: ✅ 无限次
- Private仓库: ✅ 每月约400次

## 高级配置

### 添加自动发布

修改 `build.yml` 添加release步骤:

```yaml
    - name: Create Release
      if: startsWith(github.ref, 'refs/tags/')
      uses: softprops/action-gh-release@v1
      with:
        files: app/build/outputs/apk/debug/app-debug.apk
```

### 添加签名

添加签名密钥到GitHub Secrets:

1. Settings → Secrets → Actions
2. 添加 `KEYSTORE_BASE64`, `KEY_ALIAS`, `KEY_PASSWORD`
3. 在workflow中使用

### 构建多种版本

```yaml
strategy:
  matrix:
    variant: [debug, release]
steps:
  - run: ./gradlew assemble${{ matrix.variant }}
```

## 故障排除

### 编译失败

1. 查看Actions日志
2. 检查错误信息
3. 修复代码后重新推送

### 依赖下载慢

在workflow中添加:

```yaml
- name: Cache Gradle
  uses: actions/cache@v3
  with:
    path: |
      ~/.gradle/caches
      ~/.gradle/wrapper
    key: gradle-${{ hashFiles('**/*.gradle*') }}
```

### 无法下载APK

确保workflow成功完成，查看Artifacts部分。

## 完整示例

### 1. 创建仓库并上传

```bash
cd /home/you/cursor/weather

# 如果还没有git仓库
git init
git add .
git commit -m "Android Weather App"

# 添加远程仓库
git remote add origin https://github.com/用户名/weather-app.git

# 推送
git push -u origin main
```

### 2. 等待编译

访问: https://github.com/用户名/weather-app/actions

### 3. 下载APK

编译成功后，在Artifacts中下载。

## 本地测试workflow

可以使用 `act` 工具本地测试:

```bash
# 安装act
curl https://raw.githubusercontent.com/nektos/act/master/install.sh | sudo bash

# 运行workflow
cd /home/you/cursor/weather
act
```

## 与其他CI/CD对比

| 服务 | 免费额度 | x86_64支持 | 易用性 |
|------|---------|-----------|--------|
| GitHub Actions | ✅ 2000分钟/月 | ✅ | ⭐⭐⭐⭐⭐ |
| GitLab CI | ✅ 400分钟/月 | ✅ | ⭐⭐⭐⭐ |
| Travis CI | ⚠️ 有限 | ✅ | ⭐⭐⭐ |
| CircleCI | ✅ 6000分钟/月 | ✅ | ⭐⭐⭐⭐ |
| Azure Pipelines | ✅ 无限(公开) | ✅ | ⭐⭐⭐ |

## 推荐配置

对于我们的项目，推荐使用默认配置:

✅ 自动触发编译  
✅ 保留APK artifact  
✅ 支持手动触发  
✅ 同时构建debug和release版本

## 下一步

1. 创建GitHub账号(如果没有)
2. 创建新仓库
3. 推送代码
4. 等待自动编译
5. 下载APK并安装

---

**推荐指数**: ⭐⭐⭐⭐⭐  
**难度**: 简单  
**成功率**: 99%  
**编译时间**: 5-10分钟  
**费用**: 免费
