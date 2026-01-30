package toni.sodiumleafculling.config;

import com.google.common.collect.ImmutableList;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.taumc.celeritas.api.options.OptionIdentifier;
import org.taumc.celeritas.api.options.control.CyclingControl;
import org.taumc.celeritas.api.options.control.TickBoxControl;
import org.taumc.celeritas.api.options.structure.OptionFlag;
import org.taumc.celeritas.api.options.structure.OptionGroup;
import org.taumc.celeritas.api.options.structure.OptionImpl;
import org.taumc.celeritas.api.options.structure.OptionPage;
import toni.sodiumleafculling.LeafCullingMode;

import java.util.ArrayList;
import java.util.List;

public class LeafCullingOptionsPage {
    private static final CeleritasLeafCullingOptionsStorage optionsStorage = new CeleritasLeafCullingOptionsStorage();

    public static OptionPage celeritasLeafCulling() {
        final List<OptionGroup> groups = new ArrayList<>();

        final String MOD_ID = "celeritasleafculling";

        groups.add(OptionGroup.createBuilder()
                .setId(OptionIdentifier.create(MOD_ID, "common"))
                .add(OptionImpl.createBuilder(LeafCullingMode.class, optionsStorage)
                        .setId(OptionIdentifier.create(MOD_ID, "mode", LeafCullingMode.class))
                        .setName(TextComponent.translatable("sodiumleafculling.options.leaf_culling.mode"))
                        .setTooltip(TextComponent.translatable("sodiumleafculling.options.leaf_culling.mode.desc",
                                LeafCullingMode.NONE.getLocalizedName(),
                                LeafCullingMode.HOLLOW.getLocalizedName(),
                                LeafCullingMode.SOLID_AGGRESSIVE.getLocalizedName(),
                                LeafCullingMode.SOLID.getLocalizedName()))
                        .setControl(option -> new CyclingControl<>(option, LeafCullingMode.class))
                        .setBinding((options, value) -> LeafCullingConfig.cullingMode = (value),
                                (options) -> LeafCullingConfig.cullingMode)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .build());

        OptionIdentifier<Void> pageId = OptionIdentifier.create(MOD_ID, "page");
        TextComponent pageName = TextComponent.translatable("sodiumleafculling.options.leaf_culling.page");

        return new OptionPage(pageId, pageName, ImmutableList.copyOf(groups));
    }
}