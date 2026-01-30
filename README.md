# CeleritasLeafCulling
### Fork of SodiumLeafCulling for Celeritas on 1.12.2

**This mod enables Celeritas Fast Block Renderer if you notice issues with rendering modded blocks please report the issue here**

If you are using Celeritas Extra 0.2.2 make sure you disabled Leaf culling in Celeritas extra settings

Compile Celeritas yourself from [here](https://git.taumc.org/embeddedt/celeritas) or grab [auto built jar](https://github.com/kappa-maintainer/Celeritas-auto-build/releases)

---
There are 3 culling methods

- **Hollow** - behaves like Optifine's smart option. Gives best performance, looks worst out of all options.
- **Solid agressive** - replaces leaves that are fully surrounded horizontally (north, south, east, west) but ignores vertical neighbors. Better performance than Solid but looks worse.
- **Solid** replaces leaves fully surrounded on all six sides (up, down, north, south, east, west) with a solid block. Looks best out of all options, almost visually equal to Vanilla fancy option
  
### Benchmark (9800x3d,7900xtx 3440x1440)
In Twilight Forest on Hollow option FPS went from **540** to **740** with 32RD

In Twilight Forest on Solid option FPS went from **540** to **640** with 32RD

### Special Thanks

- **Txni** – for the original SodiumLeafCulling
- **Embeddedt** – for creating Celeritas and thus giving me the motivation to make this port
