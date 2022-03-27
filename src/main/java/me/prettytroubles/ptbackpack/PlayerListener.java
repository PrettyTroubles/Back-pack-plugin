package me.prettytroubles.ptbackpack;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerListener implements Listener{
    PtBackpack plugin;
    public PlayerListener(PtBackpack plugin)
    {
        this.plugin = plugin;


    }

    public void onInventoryClick(InventoryClickEvent event)
    {
        plugin.getLogger().info(event.getWhoClicked().getName());
        plugin.getLogger().info(event.getClickedInventory().getType().name());
        plugin.getLogger().info(event.getCurrentItem().getDisplayName());
        plugin.getLogger().info(event.getClick().name());


    }
}
