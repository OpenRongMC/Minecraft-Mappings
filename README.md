# MTR 平台映射与兼容层

简体中文 · [English](./README.en.md)

供交通套件共享的 Java 平台适配层，覆盖渲染提交、世界和 HUD 钩子，以及 SDL 键鼠输入等 26.3 接口。主要读者是移植与扩展开发者。

## 下载与安装

- 版本 · [v4.0.5-port.1+26.3](https://github.com/OpenRongMC/Minecraft-Mappings/releases/tag/v4.0.5-port.1%2B26.3) (已发布)
- 下载文件 · `rongmc-mapping-4.0.5-port.1.26.3.jar`
- 运行环境 · Minecraft Java 26.3, Fabric, Java 25
- Fabric Loader · `>=0.20.1`
- Fabric 模组 ID · `rongmc_mapping`
- JAR 声明的依赖 · [Fabric API](https://modrinth.com/mod/fabric-api)

完整 MTR 套件通常已经携带其匹配的适配层。只有所选发布明确要求单独安装时，才额外使用本仓的 JAR。

## 如何验证

改动适配层后，在同一套工作区构建 MTR 和扩展，验证资源重载、世界渲染、图标和鼠标点击。排查问题时同时记录 MTR 与映射层构建。

## 开发

与同一版本的 MTR 和平台映射一起联调。交通套件的 RongMC 发布构建使用 Java 25，应以配套工作区和发行 JAR 的元数据为准。

## 文档与反馈

- [原 README（旧版指南、贡献者与署名）](https://github.com/OpenRongMC/Minecraft-Mappings/blob/2c9f51ab23edcfd23c5da66be29b5fb6eb7d266c/README.md)
- [我的世界铁路（MTR）](https://github.com/OpenRongMC/Minecraft-Transit-Railway)
- [报告 RongMC 移植问题](https://github.com/OpenRongMC/Minecraft-Mappings/issues) · 请附游戏版本、两端 JAR 文件名、复现步骤和相关日志。

## 许可与署名

请参阅本仓库的 [LICENSE](./LICENSE) 及原项目的许可、署名和第三方资源说明。
