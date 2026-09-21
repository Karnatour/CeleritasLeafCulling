package toni.sodiumleafculling;

import net.minecraft.util.text.TextFormatting;

public enum LeafCullingMode {
    NONE(TextFormatting.GRAY, "sodiumleafculling.options.leaf_culling.none"),
    HOLLOW(TextFormatting.RED, "sodiumleafculling.options.leaf_culling.hollow"),
    SOLID_AGGRESSIVE(TextFormatting.YELLOW, "sodiumleafculling.options.leaf_culling.solid_aggressive"),
    SOLID(TextFormatting.GREEN, "sodiumleafculling.options.leaf_culling.solid");

    private final TextFormatting formatting;
    private final String translationKey;

    LeafCullingMode(TextFormatting formatting, String translationKey) {
        this.formatting = formatting;
        this.translationKey = translationKey;
    }

    public TextFormatting getFormatting() { return formatting; }
    public String getTranslationKey() { return translationKey; }
}