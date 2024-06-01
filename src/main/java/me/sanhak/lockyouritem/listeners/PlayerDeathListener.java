package me.sanhak.lockyouritem.listeners;

import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import me.sanhak.lockyouritem.main.Main;
import me.sanhak.lockyouritem.utils.ItemStackUtils;
import me.sanhak.lockyouritem.utils.ListUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

public class PlayerDeathListener implements Listener {

    private Map<Player, Set<ItemStack>> lockedItems = Maps.newHashMap();

    private Main plMain;

    public PlayerDeathListener(Main plMain) {
        this.plMain = plMain;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player diedPlayer = event.getEntity().getPlayer();
        Set<ItemStack> playerItems = Sets.newHashSet();

        boolean keepInv = plMain.getConfig().getBoolean("keep-inventory");

        for (ItemStack itemStack : diedPlayer.getInventory().getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR || !itemStack.hasItemMeta())
                continue;

            else {
                if (ItemStackUtils.hasLore(itemStack)
                        && ListUtils.contains(itemStack.getItemMeta().getLore(), StringUtils.format("&C&LLOCKED"))) {

                    if (keepInv) {
                        playerItems.add(itemStack.clone());
                    }
                    if (event.getDrops().contains(itemStack)) {
                        event.getDrops().remove(itemStack);
                    }
                }
            }
        }

        if (keepInv) {
            lockedItems.put(diedPlayer, playerItems);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        boolean keepInv = plMain.getConfig().getBoolean("keep-inventory");

        if (!(lockedItems.containsKey(player)) || !keepInv)
            return;

        Set<ItemStack> playerItems = lockedItems.get(player);

        for (ItemStack itemStack : playerItems) {
            player.getInventory().addItem(itemStack);
        }
    }

}
