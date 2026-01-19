package toni.sodiumleafculling;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.embeddedt.embeddium.impl.gui.framework.TextFormattingStyle;
import org.embeddedt.embeddium.impl.gui.options.TextProvider;
import org.jetbrains.annotations.NotNull;

@net.minecraftforge.fml.common.Optional.Interface(
        iface = "org.embeddedt.embeddium.impl.gui.options.TextProvider",
        modid = "celeritas"
)
public enum LeafCullingMode implements TextProvider {
    NONE(TextFormatting.WHITE,"options.leaf_culling.none"),
    HOLLOW(TextFormatting.RED, "options.leaf_culling.hollow"),
    SOLID(TextFormatting.YELLOW, "options.leaf_culling.solid"),
    SOLID_AGGRESSIVE(TextFormatting.GREEN, "options.leaf_culling.solid_aggressive");

    private final TextComponent name;

    LeafCullingMode(@NotNull TextFormatting formatting, @NotNull String translationKey) {
        this.name = TextComponent.translatable(translationKey).withStyle(TextFormattingStyle.valueOf(formatting.name()));
    }

    @Override
    public TextComponent getLocalizedName() {
        return this.name;
    }

    public boolean isSolid() {
        return this == SOLID || this == SOLID_AGGRESSIVE;
    }
}
