import net.labymod.api.addon.AddonConfig;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemHighlighterConfiguration extends AddonConfig {

    private final ConfigProperty<Map<String, String>> highlightWords = new ConfigProperty<>(new HashMap<>());

    @Override
    public ConfigProperty<Boolean> enabled() {
        return new ConfigProperty<>(true);
    }

    public ConfigProperty<Map<String, String>> highlightWords() {
        return highlightWords;
    }

    @Override
    public void loadConfig() {
        if (highlightWords.get().isEmpty()) {
            highlightWords.set(loadDefaultConfig());
        }
    }

    private Map<String, String> loadDefaultConfig() {
        try (InputStream inputStream = getClass().getResourceAsStream("/default_config.json")) {
            if (inputStream != null) {
                return new Gson().fromJson(new InputStreamReader(inputStream), new TypeToken<Map<String, String>>() {}.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
