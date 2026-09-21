package toni.sodiumleafculling.config;

import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;

public class ActiniumOptionsListener {
    public static void onActiniumOptionsConstruct(OptionGUIConstructionEvent event) {
        event.addPage(ActiniumLeafCullingOptionsPage.actiniumLeafCulling());
    }
}
