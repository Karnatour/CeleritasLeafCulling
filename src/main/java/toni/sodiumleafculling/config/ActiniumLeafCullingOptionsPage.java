package toni.sodiumleafculling.config;

import com.google.common.collect.ImmutableList;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.structure.OptionFlag;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.embeddedt.embeddium.impl.gui.framework.TextFormattingStyle;
import toni.sodiumleafculling.LeafCullingMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActiniumLeafCullingOptionsPage {
    private static final ActiniumLeafCullingOptionsStorage optionsStorage = new ActiniumLeafCullingOptionsStorage();

    private static TextComponent nameOf(LeafCullingMode mode) {
        return TextComponent.translatable(mode.getTranslationKey())
                .withStyle(TextFormattingStyle.valueOf(mode.getFormatting().name()));
    }

    public static OptionPage actiniumLeafCulling() {
        final List<OptionGroup> groups = new ArrayList<>();

        final String MOD_ID = "celeritasleafculling";

        final TextComponent[] names = Arrays.stream(LeafCullingMode.values())
                .map(ActiniumLeafCullingOptionsPage::nameOf)
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