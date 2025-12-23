# 应用图标说明

应用需要以下图标资源文件。您可以使用Android Studio的图标生成工具或在线工具生成这些图标。

## 需要的图标文件

### mipmap-hdpi (160dpi)
- `ic_launcher.png` (72x72 px)
- `ic_launcher_round.png` (72x72 px)
- `ic_launcher_foreground.png` (108x108 px)

### mipmap-mdpi (160dpi)
- `ic_launcher.png` (48x48 px)
- `ic_launcher_round.png` (48x48 px)
- `ic_launcher_foreground.png` (108x108 px)

### mipmap-xhdpi (320dpi)
- `ic_launcher.png` (96x96 px)
- `ic_launcher_round.png` (96x96 px)
- `ic_launcher_foreground.png` (108x108 px)

### mipmap-xxhdpi (480dpi)
- `ic_launcher.png` (144x144 px)
- `ic_launcher_round.png` (144x144 px)
- `ic_launcher_foreground.png` (108x108 px)

### mipmap-xxxhdpi (640dpi)
- `ic_launcher.png` (192x192 px)
- `ic_launcher_round.png` (192x192 px)
- `ic_launcher_foreground.png` (108x108 px)

## 生成图标的方法

### 方法1: 使用Android Studio
1. 右键点击 `res` 文件夹
2. 选择 `New` -> `Image Asset`
3. 选择 `Launcher Icons (Adaptive and Legacy)`
4. 上传您的图标图片
5. 点击 `Next` 和 `Finish`

### 方法2: 使用在线工具
- https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html
- https://www.appicon.co/

### 方法3: 临时使用默认图标
如果暂时没有图标，Android Studio会在构建时使用默认的Android图标。

## 注意事项

- 图标应该是PNG格式
- 背景应该是透明的（foreground图标）
- 确保图标在不同尺寸下都清晰可见
