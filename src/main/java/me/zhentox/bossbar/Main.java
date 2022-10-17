package me.zhentox.bossbar;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
    String pluginName = ChatColor.GOLD+"BossBar";
    String configPath;

    public void onEnable() {
        System.out.println("[BossBar] Se inició correctamente.");
        reigsterConfig();
        getServer().getPluginManager().registerEvents(new Bar(this), this);
    }

    public void reigsterConfig() {
        File config = new File(this.getDataFolder(),"config.yml");
        configPath = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }    
}