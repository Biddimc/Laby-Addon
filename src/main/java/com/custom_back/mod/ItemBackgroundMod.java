package main.java.com.custom_back.mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ItemBackgroundMod.MODID, name = ItemBackgroundMod.NAME, version = ItemBackgroundMod.VERSION)
public class ItemBackgroundMod {
    public static final String MODID = "itembackgroundmod";
    public static final String NAME = "Item Background Mod";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
    }
}