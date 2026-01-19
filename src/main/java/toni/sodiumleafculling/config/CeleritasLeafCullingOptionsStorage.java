package toni.sodiumleafculling.config;

import net.minecraftforge.common.config.ConfigManager;
import org.taumc.celeritas.api.options.structure.OptionStorage;

public class CeleritasLeafCullingOptionsStorage implements OptionStorage<LeafCullingConfig> {

    @Override
    public void save() {
        ConfigManager.sync("celeritasleafculling", net.minecraftforge.common.config.Config.Type.INSTANCE);
    }

    @Override
    public LeafCullingConfig getData() {
        return null;
    }
}
