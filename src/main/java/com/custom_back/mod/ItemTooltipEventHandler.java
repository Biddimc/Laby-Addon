package main.java.com.custom_back.mod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.IResource;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemTooltipEventHandler {
    private static final String CONFIG_PATH = "custom_back/backgrounds.json";
    private static Map<String, String> backgroundMap = new HashMap<>();

    public ItemTooltipEventHandler() {
        loadBackgroundConfig();
    }

    private void loadBackgroundConfig() {
        try {
            Minecraft mc = Minecraft.getMinecraft();
            IResource resource = mc.getResourceManager().getResource(new ResourceLocation(CONFIG_PATH));
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            backgroundMap = gson.fromJson(reader, type);

            reader.close();
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der JSON-Datei: " + e.getMessage());
        }
    }

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.PostBackground event) {
        List<String> tooltip = event.getLines();
        Minecraft mc = Minecraft.getMinecraft();

        // Standard-Hintergrund
        ResourceLocation background = null;

        // Prüft Begriffe aus der JSON-Datei
        for (String line : tooltip) {
            for (Map.Entry<String, String> entry : backgroundMap.entrySet()) {
                if (line.contains(entry.getKey())) {
                    background = new ResourceLocation("examplemod", entry.getValue());
                    break;
                }
            }
            if (background != null) break; // Stoppt, sobald ein Treffer gefunden wurde
        }

        // Falls ein passender Hintergrund gefunden wurde, zeichnen
        if (background != null) {
            mc.getTextureManager().bindTexture(background);
            Gui.drawModalRectWithCustomSizedTexture(event.getX(), event.getY(), 0, 0, event.getWidth(), event.getHeight(), event.getWidth(), event.getHeight());
        }
    }
}
