# Minecraft Mappings（RongMC Transport Compatibility）

为 Minecraft Transit Railway 及其附属提供的 **平台适配 / 映射层**（原 Minecraft-Mappings 思路），使交通套件能在目标 Minecraft 版本上编译与运行。

本模组一般由 `rongmc_mtr` jar **内嵌/依赖**，客户端/服务端通常不必单独安装；单独发布便于源码审阅与排错。

---

## RongMC · Minecraft 26.3 Fabric 移植说明

本仓库是 RongMC 维护的 **Minecraft 26.3 / Fabric / Java 25** 移植构建，不是上游官方发行渠道。

### 与原版的主要差异
- 面向 Minecraft 26.3；Fabric id `rongmc_mapping`。
- 内含渲染矩阵、世界渲染等 mixin/适配代码，供 MTR 套件共用。

### 已知问题 / 限制
- 不要与其它冲突的“假映射/重映射”核心同时乱装。
- 单独加载而无 MTR 时几乎没有可玩内容。

### 构建与安装
- Fabric mod id：`rongmc_mapping`（`provides`：`mapping`，旧依赖名一般仍可解析）
- Release 产物见本仓库 Releases（jar 名带 `rongmc-` 前缀）
- 许可证：本仓以 **GPL-3.0** 发布（见 `LICENSE`）；请同时尊重上游原作者条款与署名

