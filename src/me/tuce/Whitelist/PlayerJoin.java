package me.tuce.Whitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoin implements Listener {

    private final Main plugin;
    public PlayerJoin(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.getServer().getPluginManager().disablePlugin(plugin);
            }
        }, ((Integer) plugin.getCustomConfig().getInt("seconds_to_wait") * 20));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        List<?> whitelist = plugin.getCustomConfig().getList("whitelist");
        if (!whitelist.contains(player.getName()))
            player.kickPlayer(plugin.getCustomConfig().getString("message_for_kicked_players"));
    }
}
