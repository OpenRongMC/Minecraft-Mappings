# MTR Platform Mappings and Compatibility

[简体中文](./README.md) · English


A shared Java adapter for the transport suite, covering render submission, world and HUD hooks, and SDL keyboard and mouse input on 26.3. It is primarily intended for port and addon developers.

## What changed in the RongMC 26.3 port

The adapter puts render submission, world and HUD callbacks and SDL input translation behind shared interfaces while preserving the Java API used by addons.

## Installation

Download **v4.0.5-port.1+26.3** from [GitHub Releases](https://github.com/OpenRongMC/Minecraft-Mappings/releases/tag/v4.0.5-port.1%2B26.3) for Minecraft Java **26.3** on **Fabric** with **Java 25**. Dependencies include [Fabric API](https://modrinth.com/mod/fabric-api). A complete MTR suite normally already includes its matching adapter. Obtain the separate JAR only when the selected release calls for it.

## License and credits

See [LICENSE](./LICENSE) and the original project credits.
