package me.prettytroubles.ptbackpack;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener{
    PtBackpack plugin;
    private List<ItemStack> testBackPackStorage = new ArrayList<>();

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
            Player player = (Player) event.getWhoClicked();
            openBackpack(player);
        }
    }
    @EventHandler
    public void onCloseBackPack(InventoryCloseEvent event)
    {
        Inventory backpack = event.getInventory();
        if (event.getView().getTitle().equals("Small Backpack"))
        {
            testBackPackStorage.clear();
            for (ItemStack itemStack:backpack.getContents())
            {
                testBackPackStorage.add(itemStack);
            }

        }


    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event.getItem().getType() == Material.BROWN_MUSHROOM)
        {
            if(event.getAction().isRightClick())
            {
                if (!event.getClickedBlock().getType().isInteractable())
                {
                    openBackpack(event.getPlayer());
                    event.setCancelled(true);
                }
            }
        }

    }
    private void openBackpack(Player player)
    {
        Inventory backpack = plugin.getServer().createInventory(null, 9,"Small Backpack");
        for (ItemStack itemStack : testBackPackStorage)
        {
            if (itemStack == null)
            {
                continue;
            }
            backpack.addItem(itemStack);

        }
        new BukkitRunnable()
        {

            @Override
            public void run() {

                player.openInventory(backpack);
            }
        }.runTaskLater(plugin,1);
    }


}
