package me.prettytroubles.ptbackpack;

import org.bukkit.plugin.java.JavaPlugin;

public class PtBackpack extends JavaPlugin {
    @Override
    public void onEnable() {

        PlayerListener playerListener = new PlayerListener(this);
        getServer().getPluginManager().registerEvents(playerListener,this);
    }
}
