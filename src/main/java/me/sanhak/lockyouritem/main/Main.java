package me.sanhak.lockyouritem.main;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.sanhak.lockyouritem.commands.LockCommand;
import me.sanhak.lockyouritem.commands.ReloadCommand;
import me.sanhak.lockyouritem.listeners.PlayerDeathListener;
import me.sanhak.lockyouritem.listeners.PlayerDropItemListener;
import me.sanhak.lockyouritem.utils.StringUtils;

public class Main extends JavaPlugin {

	@Getter
	private static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		saveConfig();
		Bukkit.getConsoleSender().sendMessage(StringUtils.format("&c&LLockYourItems &fplugin has been &A&LENABLED"));
		new LockCommand(this);
		new ReloadCommand(this);
		Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
	}

	@Override
	public void onDisable() {
		instance = null;
		Bukkit.getConsoleSender().sendMessage(StringUtils.format("&c&LLockYourItems &fplugin has been &c&LDISABLED"));
	}

}
