package toni.sodiumleafculling;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taumc.celeritas.api.OptionGUIConstructionEvent;
import toni.sodiumleafculling.config.CeleritasOptionsListener;
import toni.sodiumleafculling.config.ConfigEventHandler;

@Mod(modid = toni.sodiumleafculling.celeritasleafculling.Reference.MOD_ID, name = toni.sodiumleafculling.celeritasleafculling.Reference.MOD_NAME, version = toni.sodiumleafculling.celeritasleafculling.Reference.VERSION, clientSideOnly = true, acceptableRemoteVersions = "*", dependencies = "required-after-client:celeritas@[2.4.0,)")
public class CeleritasLeafCulling
{
    public static final Logger LOGGER = LogManager.getLogger(toni.sodiumleafculling.celeritasleafculling.Reference.MOD_NAME);

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        if (Loader.isModLoaded("celeritas")) {
            try {
                Class.forName("org.taumc.celeritas.api.OptionGUIConstructionEvent");
                OptionGUIConstructionEvent.BUS.addListener(CeleritasOptionsListener::onCeleritasOptionsConstruct);
                LOGGER.info("Celeritas detected, setting fastBlockRenderer property to true");
                System.setProperty("celeritas.useVintageFastBlockRenderer", "true");
            } catch (Throwable t) {
                if (t instanceof NoClassDefFoundError) {
                    LOGGER.error("Celeritas version is too old use 2.4.0 or newer");
                } else {
                    LOGGER.error("Unable to check if Celeritas is up-to-date", t);
                }
            }
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
        }
    }
}
