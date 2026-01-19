/*
 * Copyright © 2020 LambdAurora <email@lambdaurora.dev>
 *
 * This file is part of SodiumDynamicLights.
 *
 * Licensed under the MIT License. For more information,
 * see the LICENSE file.
 */

package toni.sodiumleafculling.config;

import net.minecraftforge.common.config.Config;
import toni.sodiumleafculling.LeafCullingMode;

@Config(modid = "celeritasleafculling")
public class LeafCullingConfig {
    public static LeafCullingMode cullingMode = LeafCullingMode.SOLID_AGGRESSIVE;
}
