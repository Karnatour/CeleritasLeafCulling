package toni.sodiumleafculling.config;

import org.taumc.celeritas.api.OptionGUIConstructionEvent;

public class CeleritasOptionsListener {
    public static void onCeleritasOptionsConstruct(OptionGUIConstructionEvent event) {
        event.addPage(LeafCullingOptionsPage.celeritasLeafCulling());
    }
}
