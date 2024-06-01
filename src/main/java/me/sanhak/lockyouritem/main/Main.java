package me.sanhak.lockyouritem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.sanhak.lockyouritem.commands.LockCommand;
import me.sanhak.lockyouritem.listeners.PlayerDeathListener;
import me.sanhak.lockyouritem.listeners.PlayerDropItemListener;
import me.sanhak.lockyouritem.utils.StringUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		saveConfig();
		Bukkit.getConsoleSender().sendMessage(StringUtils.format("&c&LLockYourItems &fplugin has been &A&LENABLED"));
		getCommand("lock").setExecutor(new LockCommand());
		Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(StringUtils.format("&c&LLockYourItems &fplugin has been &c&LDISABLED"));
	}

	public static Main getInstance() {
		return instance;
	}

}
