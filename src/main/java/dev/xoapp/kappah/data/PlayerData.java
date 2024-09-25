package dev.xoapp.kappah.data;

import cn.nukkit.utils.Config;
import dev.xoapp.kappah.Loader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private final Config _config;

    public PlayerData() {
        File file = new File(Loader.getInstance().getDataFolder() + "kappah/players.json");
        _config = new Config(file, Config.JSON);
    }

    public void setData(String key, Object data) {
        _config.set(key, data);
        _config.save();
    }

    public Object getData(String key) {
        return _config.get(key);
    }

    public Map<String, Object> getSavedData() {
        return _config.getAll();
    }
}
