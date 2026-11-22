# MTR Platform Mappings and Compatibility

[简体中文](./README.md) · English

A shared Java adapter for the transport suite, covering render submission, world and HUD hooks, and SDL keyboard and mouse input on 26.3. It is primarily intended for port and addon developers.

## Download and installation

- Version · [v4.0.5-port.1+26.3](https://github.com/OpenRongMC/Minecraft-Mappings/releases/tag/v4.0.5-port.1%2B26.3) (Release)
- Release asset · `rongmc-mapping-4.0.5-port.1.26.3.jar`
- Runtime · Minecraft Java 26.3, Fabric, Java 25
- Fabric Loader · `>=0.20.1`
- Fabric mod ID · `rongmc_mapping`
- Dependencies declared by the JAR · [Fabric API](https://modrinth.com/mod/fabric-api)

A complete MTR suite normally already includes its matching adapter. Obtain the separate JAR only when the selected release calls for it.

## How to try it

After changing the adapter, build it alongside MTR and addons and test resource reloads, level rendering, icons and mouse clicks. Include both MTR and mapping builds in compatibility reports.

## Development

Develop and test alongside the matching MTR and platform mappings. The RongMC transport build uses Java 25. Use the coordinated workspace and release metadata when reproducing a release.

## Documentation and support

- [Previous README (historical guide, contributors and credits)](https://github.com/OpenRongMC/Minecraft-Mappings/blob/2c9f51ab23edcfd23c5da66be29b5fb6eb7d266c/README.md)
- [Minecraft Transit Railway (MTR)](https://github.com/OpenRongMC/Minecraft-Transit-Railway)
- [Report a RongMC port issue](https://github.com/OpenRongMC/Minecraft-Mappings/issues) · Include the game version, client and server JAR names, reproduction steps and relevant logs.

## License and credits

See this repository’s [LICENSE](./LICENSE) and the original project’s license, credits and third-party asset notices.
