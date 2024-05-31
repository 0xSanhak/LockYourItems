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

import me.sanhak.lockyouritem.utils.ItemStackUtils;
import me.sanhak.lockyouritem.utils.ListUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

public class PlayerDeathListener implements Listener {

    private Map<Player, Set<ItemStack>> lockedItems = Maps.newHashMap();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player diedPlayer = event.getEntity().getPlayer();
        Set<ItemStack> playerItems = Sets.newHashSet();
        for (ItemStack itemStack : diedPlayer.getInventory().getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR || !itemStack.hasItemMeta())
                continue;

            else {
                if (ItemStackUtils.hasLore(itemStack)
                        && ListUtils.contains(itemStack.getItemMeta().getLore(), StringUtils.format("&C&LLOCKED"))) {
                    playerItems.add(itemStack.clone());
                    if (event.getDrops().contains(itemStack)) {
                        event.getDrops().remove(itemStack);
                    }
                }
            }
        }

        lockedItems.put(diedPlayer, playerItems);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (!(lockedItems.containsKey(player)))
            return;

        Set<ItemStack> playerItems = lockedItems.get(player);

        for (ItemStack itemStack : playerItems) {
            player.getInventory().addItem(itemStack);
        }
    }

}
