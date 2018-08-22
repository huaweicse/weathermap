# weathermap
本文档通过一个示例向您展示CSE微服务引擎的基本能力，以及对新服务快速开发和已有服务零改造接入的支持能力。示例是一个天气预报应用，该应用可以为您提供世界各地当前的天气详情以及未来3天的预报。

## 使用指南

请参见  [微服务引擎 快速入门](https://support.huaweicloud.com/qs-cse/cse_qs_0001.html)


## 开发指南

本项目需要JDK 1.8或更新。

### 获取代码

```
git clone https://github.com/cse-sample/weathermap.git
```

下载完的代码可以用Eclipse或IntelliJ打开进行开发。

### 编译打包
```
./mvnw clean install
```

命令成功完成后，你可以看到生成的最终发布文件：
`dist/target/weathermap-1.0.0.zip`

