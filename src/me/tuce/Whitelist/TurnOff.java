package me.tuce.Whitelist;

import org.bukkit.Bukkit;

public class TurnOff {
    private final Main plugin;
    public TurnOff(Main plugin) {
        this.plugin = plugin;

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.setWhitelist(false);
                plugin.getServer().getPluginManager().disablePlugin(plugin);
            }
        }, ((Integer) plugin.getCustomConfig().getInt("seconds_to_wait") * 20));
    }
}
