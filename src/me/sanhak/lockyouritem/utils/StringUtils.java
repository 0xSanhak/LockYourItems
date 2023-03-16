package me.sanhak.lockyouritem.utils;

import org.bukkit.ChatColor;

public class StringUtils {

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
