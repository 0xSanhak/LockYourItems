package me.sanhak.lockyouritem.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtils {

	public static boolean isNull(ItemStack itemstack) {
		if (itemstack == null || itemstack.getType() == Material.AIR) {
			return true;
		}
		return false;
	}

	public static boolean hasMeta(ItemStack itemstack) {
		if (itemstack != null) {
			if (itemstack.hasItemMeta() || itemstack.getItemMeta() != null) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasLore(ItemStack itemstack) {
		if (itemstack != null) {
			if (hasMeta(itemstack)) {
				if (itemstack.getItemMeta().hasLore() || itemstack.getItemMeta().getLore() != null) {
					return true;
				}
			}

		}
		return false;
	}

}
