package blockspawnmobs.blockspawnmobs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {

    private FileConfiguration filecfg;
    private BlockSpawnMobs plugin;
    private File file;

    public ConfigFile(BlockSpawnMobs plugin) {
        this.plugin = plugin;
        setupCfgFile();
        if (getCfgFile().isSet("BlockSpawnMob"))
            saveCfgFile();
        else
            firstFill();
    }

    private void setupCfgFile() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException localIOException) {
            }
        }
        new YamlConfiguration();
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    private FileConfiguration getCfgFile() {
        return filecfg;
    }

    public void saveCfgFile() {
        try {
            filecfg.save(file);
        } catch (IOException localIOException) {
        }
    }

    public void reloadCfgFile() {
        new YamlConfiguration();
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    private void firstFill() {
        getCfgFile().set("BlockSpawnMob", " Конфигурация");
        getCfgFile().set("EnableBlockSpawn", true);
        getCfgFile().set("BlockMobs", Arrays.asList(new String[] {"ZOMBIE"}));
        saveCfgFile();
    }

    public List<String> getAllowedMobs() {
        return getCfgFile().getStringList("BlockMobs");
    }

    public Boolean isEnable() {
        return getCfgFile().getBoolean("EnableBlockSpawn");
    }

}