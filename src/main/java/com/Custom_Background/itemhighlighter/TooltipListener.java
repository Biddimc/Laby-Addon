import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.RenderTooltipEvent;
import net.labymod.api.client.world.item.tooltip.ItemTooltip;
import net.labymod.api.client.world.item.ItemStack;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import java.util.Map;

public class TooltipListener {

    private final ItemHighlighterConfiguration config;

    public TooltipListener(ItemHighlighterConfiguration config) {
        this.config = config;
    }

    @Subscribe
    public void onRenderTooltip(RenderTooltipEvent event) {
        ItemTooltip tooltip = event.tooltip();
        ItemStack itemStack = event.itemStack();

        if (itemStack == null || tooltip == null) return;

        // Lade die konfigurierten Schlüsselwörter und zugehörigen Texturen
        Map<String, String> highlightWords = config.highlightWords().get();

        for (String line : tooltip.getText()) {
            for (Map.Entry<String, String> entry : highlightWords.entrySet()) {
                if (line.contains(entry.getKey())) {
                    event.setBackground(Icon.texture(new ResourceLocation("labymod", entry.getValue())));
                    return;
                }
            }
        }
    }
}

