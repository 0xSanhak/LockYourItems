package me.sanhak.lockyouritem.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtils {


	ItemStackUtils() {
		throw new IllegalStateException("Initiating Utility class!");
	}
	public static boolean isNull(ItemStack itemstack) {
        return itemstack == null || itemstack.getType() == Material.AIR;
    }

	public static boolean hasMeta(ItemStack itemstack) {
		if (itemstack != null) {
            return itemstack.hasItemMeta() || itemstack.getItemMeta() != null;

		}
		return false;
	}

	public static boolean hasLore(ItemStack itemstack) {
		return !isNull(itemstack) && hasMeta(itemstack) && (itemstack.getItemMeta().hasLore() || itemstack.getItemMeta().getLore() != null);
	}


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
