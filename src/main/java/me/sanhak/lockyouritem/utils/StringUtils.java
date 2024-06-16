package me.sanhak.lockyouritem.utils;

import org.bukkit.ChatColor;

public final class StringUtils {

	StringUtils() {
		throw new IllegalStateException("Initiating Utility class!");
	}
	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
