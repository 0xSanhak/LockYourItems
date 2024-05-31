package me.sanhak.lockyouritem.methods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.sanhak.lockyouritem.utils.StringUtils;

public class ItemCreator {

	public static ItemStack createLockedItem(ItemStack oldItemStack) {
		ItemStack newItemStack = oldItemStack.clone();
		ItemMeta newItemStackMeta = newItemStack.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		loreList.add(StringUtils.format(" "));
		loreList.add(StringUtils.format("&C&LLOCKED"));
		loreList.add(StringUtils.format(" "));
		newItemStackMeta.setLore(loreList);
		newItemStack.setItemMeta(newItemStackMeta);
		return newItemStack;
	}

}
