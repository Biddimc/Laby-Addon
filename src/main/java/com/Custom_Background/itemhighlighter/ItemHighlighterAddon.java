import net.labymod.api.addon.LabyAddon;

public class ItemHighlighterAddon extends LabyAddon<ItemHighlighterConfiguration> {

    @Override
    protected void enable() {
        this.registerSettingCategory();
        this.registerListener(new TooltipListener(this.configuration()));
    }

    @Override
    protected Class<ItemHighlighterConfiguration> configurationClass() {
        return ItemHighlighterConfiguration.class;
    }
}
