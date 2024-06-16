package me.sanhak.lockyouritem.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.sanhak.lockyouritem.utils.ItemStackUtils;
import me.sanhak.lockyouritem.utils.ListUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

public class PlayerDropItemListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDropLockedItem(PlayerDropItemEvent playerDropItemEvent) {
		ItemStack droppedItem = playerDropItemEvent.getItemDrop().getItemStack();
		if (ItemStackUtils.hasLore(droppedItem)) {
			List<String> droppedItemLore = droppedItem.getItemMeta().getLore();
			if (ListUtils.contains(droppedItemLore, StringUtils.format("&C&LLOCKED"))) {
				playerDropItemEvent.setCancelled(true);
			}
		}
		
	}

}
