package toni.sodiumleafculling;

import net.minecraft.util.text.TextFormatting;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.embeddedt.embeddium.impl.gui.framework.TextFormattingStyle;
import org.embeddedt.embeddium.impl.gui.options.TextProvider;
public enum LeafCullingMode implements TextProvider {
    NONE(TextFormatting.GRAY,"sodiumleafculling.options.leaf_culling.none"),
    HOLLOW(TextFormatting.RED, "sodiumleafculling.options.leaf_culling.hollow"),
    SOLID_AGGRESSIVE(TextFormatting.YELLOW, "sodiumleafculling.options.leaf_culling.solid_aggressive"),
    SOLID(TextFormatting.GREEN, "sodiumleafculling.options.leaf_culling.solid");

    private final TextComponent name;

    LeafCullingMode(TextFormatting formatting, String translationKey) {
        this.name = TextComponent.translatable(translationKey).withStyle(TextFormattingStyle.valueOf(formatting.name()));
    }

    public TextComponent getLocalizedName() {
        return this.name;
    }
}
