# 漳州天气预报应用

这是一个Android天气预报应用，显示漳州市芗城区和龙文区的天气情况，包括7天天气预报。

## 功能特性

- 显示芗城区和龙文区的当前天气信息
- 显示7天天气预报
- 显示温度、天气状况、风向、湿度等信息
- 使用百度地图天气API获取数据

## 技术栈

- **语言**: Java
- **最低SDK版本**: Android 7.0 (API 24)
- **目标SDK版本**: Android 14 (API 34)
- **编译SDK版本**: Android 14 (API 34)
- **构建工具**: Gradle 8.2
- **网络库**: Retrofit 2.9.0 + OkHttp 4.12.0
- **JSON解析**: Gson 2.10.1

## 项目结构

```
app/
├── src/main/
│   ├── java/com/weather/app/
│   │   ├── MainActivity.java          # 主Activity
│   │   ├── adapter/
│   │   │   └── ForecastAdapter.java   # 天气预报列表适配器
│   │   ├── api/
│   │   │   ├── ApiClient.java         # API客户端配置
│   │   │   └── WeatherApiService.java # 天气API接口
│   │   └── model/
│   │       ├── WeatherResponse.java   # 天气响应模型
│   │       ├── WeatherResult.java     # 天气结果模型
│   │       ├── Location.java          # 位置信息模型
│   │       ├── CurrentWeather.java    # 当前天气模型
│   │       └── Forecast.java          # 天气预报模型
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml      # 主界面布局
│   │   │   └── item_forecast.xml      # 预报项布局
│   │   └── values/
│   │       ├── strings.xml            # 字符串资源
│   │       ├── colors.xml             # 颜色资源
│   │       └── themes.xml             # 主题资源
│   └── AndroidManifest.xml            # 应用清单文件
└── build.gradle                       # 应用构建配置
```

## 编译和打包

### 前置要求

1. **JDK 17** 或更高版本
2. **Android SDK** (API Level 34)
3. **Android NDK** (可选，用于原生代码)
4. **Gradle 8.2** 或更高版本

### 使用命令行编译

```bash
# 清理项目
./gradlew clean

# 编译Debug版本
./gradlew assembleDebug

# 编译Release版本
./gradlew assembleRelease

# 安装到设备
./gradlew installDebug
```

生成的APK文件位置：
- Debug版本: `app/build/outputs/apk/debug/app-debug.apk`
- Release版本: `app/build/outputs/apk/release/app-release.apk`

### 使用Android Studio

1. 打开Android Studio
2. 选择 "Open an existing project"
3. 选择项目目录
4. 等待Gradle同步完成
5. 点击 "Build" -> "Build Bundle(s) / APK(s)" -> "Build APK(s)"
6. 或者直接运行应用: "Run" -> "Run 'app'"

## API说明

应用使用百度地图天气API：
- 芗城区: `district_id=350602`
- 龙文区: `district_id=350603`
- API Key: `BgFd8iVrd6YSViXfEvgkeee9pFmAYqkD`

API端点: `https://api.map.baidu.com/weather/v1/`

## 权限说明

应用需要以下权限：
- `INTERNET`: 访问网络获取天气数据
- `ACCESS_NETWORK_STATE`: 检查网络连接状态

## Android 14兼容性

应用已配置为兼容Android 14 (API 34)：
- `targetSdkVersion`: 34
- `compileSdkVersion`: 34
- 已配置必要的权限和网络安全配置

## 注意事项

1. 确保设备已连接网络
2. 应用图标资源需要替换为实际图标文件
3. 如需修改API Key，请在 `MainActivity.java` 中更新 `API_KEY` 常量

## 许可证

本项目仅供学习和参考使用。
