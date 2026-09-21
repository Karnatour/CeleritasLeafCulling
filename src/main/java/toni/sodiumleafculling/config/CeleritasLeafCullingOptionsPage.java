package toni.sodiumleafculling.config;

import com.google.common.collect.ImmutableList;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.embeddedt.embeddium.impl.gui.framework.TextFormattingStyle;
import org.taumc.celeritas.api.options.OptionIdentifier;
import org.taumc.celeritas.api.options.control.CyclingControl;
import org.taumc.celeritas.api.options.structure.OptionFlag;
import org.taumc.celeritas.api.options.structure.OptionGroup;
import org.taumc.celeritas.api.options.structure.OptionImpl;
import org.taumc.celeritas.api.options.structure.OptionPage;
import toni.sodiumleafculling.LeafCullingMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeleritasLeafCullingOptionsPage {
    private static final CeleritasLeafCullingOptionsStorage optionsStorage = new CeleritasLeafCullingOptionsStorage();

    private static TextComponent nameOf(LeafCullingMode mode) {
        return TextComponent.translatable(mode.getTranslationKey())
                .withStyle(TextFormattingStyle.valueOf(mode.getFormatting().name()));
    }

    public static OptionPage celeritasLeafCulling() {
        final List<OptionGroup> groups = new ArrayList<>();
        final String MOD_ID = "celeritasleafculling";

        final TextComponent[] names = Arrays.stream(LeafCullingMode.values())
                .map(CeleritasLeafCullingOptionsPage::nameOf)
                .toArray(TextComponent[]::new);

        groups.add(OptionGroup.createBuilder()
                .setId(OptionIdentifier.create(MOD_ID, "common"))
                .add(OptionImpl.createBuilder(LeafCullingMode.class, optionsStorage)
                        .setId(OptionIdentifier.create(MOD_ID, "mode", LeafCullingMode.class))
                        .setName(TextComponent.translatable("sodiumleafculling.options.leaf_culling.mode"))
                        .setTooltip(TextComponent.translatable("sodiumleafculling.options.leaf_culling.mode.desc",
                                nameOf(LeafCullingMode.NONE),
                                nameOf(LeafCullingMode.HOLLOW),
                                nameOf(LeafCullingMode.SOLID_AGGRESSIVE),
                                nameOf(LeafCullingMode.SOLID)))
                        .setControl(option -> new CyclingControl<>(option, LeafCullingMode.class, names))
                        .setBinding((options, value) -> LeafCullingConfig.cullingMode = value,
                                (options) -> LeafCullingConfig.cullingMode)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .build());

        OptionIdentifier<Void> pageId = OptionIdentifier.create(MOD_ID, "page");
        TextComponent pageName = TextComponent.translatable("sodiumleafculling.options.leaf_culling.page");

        return new OptionPage(pageId, pageName, ImmutableList.copyOf(groups));
    }
}