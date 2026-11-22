# MTR 平台映射与兼容层

简体中文 · [English](./README.en.md)


供交通套件共享的 Java 平台适配层，覆盖渲染提交、世界和 HUD 钩子，以及 SDL 键鼠输入等 26.3 接口。主要读者是移植与扩展开发者。

## RongMC 26.3 版本的改动

移植层将渲染提交、世界与 HUD 回调及 SDL 输入转换集中到交通套件共用接口中，保留扩展调用的 Java API。

## 安装

在 [GitHub Releases](https://github.com/OpenRongMC/Minecraft-Mappings/releases/tag/v4.0.5-port.1%2B26.3) 下载 **v4.0.5-port.1+26.3**。使用 Minecraft Java **26.3**、**Fabric** 和 **Java 25**。 依赖 [Fabric API](https://modrinth.com/mod/fabric-api)。 完整 MTR 套件通常已经携带其匹配的适配层。只有所选发布明确要求单独安装时，才额外使用本仓的 JAR。

## 许可与署名

详见 [LICENSE](./LICENSE) 与原项目署名。
