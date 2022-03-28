package me.prettytroubles.ptbackpack;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerListener implements Listener{
    PtBackpack plugin;
    public PlayerListener(PtBackpack plugin)
    {
        this.plugin = plugin;


    }
     @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        plugin.getLogger().info("getwhoclicked()"+event.getWhoClicked().getName());
        plugin.getLogger().info("getclickedInventory()"+event.getClickedInventory().getType().name());
        if (event.getCurrentItem() !=null && event.getCurrentItem().getItemMeta() !=null)
        {
            plugin.getLogger().info("ItemName()"+event.getCurrentItem().getType().name());
        }
        plugin.getLogger().info("getclicked()"+event.getClick().name());

    }

    @EventHandler
    public void  onOpenBackpack(InventoryClickEvent event)
    {
        ItemStack item = event.getCurrentItem();
        if (item.getType()!= Material.BROWN_MUSHROOM)
        {
            return;
        }
        if (event.getClick()== ClickType.RIGHT)
        {
            event.setCancelled(true);
            Inventory backpack = plugin.getServer().createInventory(null, 9);
            new BukkitRunnable()
            {

                @Override
                public void run() {
                    Player player = (Player) event.getWhoClicked();
                    player.openInventory(backpack).;
                }
            }.runTaskLater(plugin,1);
        }
    }
}
