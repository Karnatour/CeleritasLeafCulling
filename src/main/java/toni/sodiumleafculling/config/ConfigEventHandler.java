package toni.sodiumleafculling.config;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigEventHandler {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if ("celeritasleafculling".equals(event.getModID())) {
            ConfigManager.sync("celeritasleafculling", net.minecraftforge.common.config.Config.Type.INSTANCE);
        }
    }
}
