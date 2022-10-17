package me.zhentox.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Bar implements Listener {

    private Main plugin;
    private FileConfiguration config = null;
    private String bossBarTextConfig = null;
    private String[] worldsConfig = null;
    private String barColorConfig = null;
    private String barStyleConfig = null;
    private BossBar bar = null;
    
    public Bar(Main plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
        bossBarTextConfig = config.getString("Config.bossBarText");
        worldsConfig = config.getString("Config.worlds").split(", ");
        barColorConfig = config.getString("Config.barColor").toUpperCase();
        barStyleConfig = config.getString("Config.barStyle").toUpperCase();
        bar = Bukkit.createBossBar(format(bossBarTextConfig), getBarColorConfig(barColorConfig), convertStyle(barStyleConfig));
    }
    
    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private static BarColor getBarColorConfig(String color) {
        switch (color) {
            case "PINK":
                return BarColor.PINK;
            case "BLUE":
                return BarColor.BLUE;
            case "RED":
                return BarColor.RED;
            case "GREEN":
                return BarColor.GREEN;
            case "YELLOW":
                return BarColor.YELLOW;
            case "PURPLE":
                return BarColor.PURPLE;
            default:
                return BarColor.WHITE;
        }
    }

    private static BarStyle convertStyle(String style) {
        switch (style) {
            case "SEGMENTED_6":
                return BarStyle.SEGMENTED_6;
            case "SEGMENTED_10":
                return BarStyle.SEGMENTED_10;
            case "SEGMENTED_12":
                return BarStyle.SEGMENTED_12;
            case "SEGMENTED_20":
                return BarStyle.SEGMENTED_20;
            default:
                return BarStyle.SOLID;
        }
    }

    @EventHandler
    public void playerWorldChange(PlayerChangedWorldEvent e) {
        for(String world : worldsConfig) {
            if(e.getPlayer().getWorld().getName().equals(world)) {
                bar.addPlayer(e.getPlayer());
                break;
            } else {
                bar.removePlayer(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for(String world : worldsConfig) {
            if(e.getPlayer().getWorld().getName().equals(world)) {
                bar.addPlayer(e.getPlayer());
                break;
            } else {
                bar.removePlayer(e.getPlayer());
            }
        }
    }
}