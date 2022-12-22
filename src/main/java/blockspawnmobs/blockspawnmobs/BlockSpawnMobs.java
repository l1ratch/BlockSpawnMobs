package blockspawnmobs.blockspawnmobs;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockSpawnMobs extends JavaPlugin implements Listener {

    private ConfigFile cfg;

    public void onEnable() {
        try {
            cfg = new ConfigFile(this);
            Bukkit.getPluginManager().registerEvents(this, this);
            Bukkit.getConsoleSender()
                    .sendMessage("§a[§aBlockSpawnMobs§a] §f-> §aПлагин включен! (DEV: Link_play)");
        } catch (Exception e) {
            Bukkit.getConsoleSender()
                    .sendMessage("§c[§aBlockSpawnMobs§c] §f-> §cОшибка. Запуск плагина остановлен! \nError:"
                            + e.getLocalizedMessage());
            this.setEnabled(false);
        }
    }

    public void onDisable() {
        Bukkit.getConsoleSender()
                .sendMessage("§c[§aBlockSpawnMobs§c] §f-> §cПлагин выключен! (DEV: Link_play)");
    }

    @EventHandler
    public void onBucketEmpty(CreatureSpawnEvent e) {
        if (cfg.isEnable())
            if (cfg.getAllowedMobs().contains(e.getEntityType().name()))
                e.setCancelled(true);
    }

}
