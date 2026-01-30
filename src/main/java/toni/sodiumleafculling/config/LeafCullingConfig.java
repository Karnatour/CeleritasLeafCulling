package toni.sodiumleafculling.config;

import net.minecraftforge.common.config.Config;
import toni.sodiumleafculling.LeafCullingMode;

@Config(modid = "celeritasleafculling")
public class LeafCullingConfig {
    public static LeafCullingMode cullingMode = LeafCullingMode.SOLID;
}
