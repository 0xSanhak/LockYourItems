package me.sanhak.lockyouritem.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtils {

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

}
