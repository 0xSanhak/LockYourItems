package me.sanhak.lockyouritem.commands;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.sanhak.lockyouritem.main.Main;
import me.sanhak.lockyouritem.utils.ItemStackUtils;
import me.sanhak.lockyouritem.utils.PlayerUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

public final class LockCommand {

	public LockCommand(@NonNull Main instance) {
		instance.getCommand("lock").setExecutor((sender, command, label, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(StringUtils.format("&cSorry , only players can perform this command !"));
				return true;
			}
			final Player player = (Player) sender;
			if (!player.hasPermission("lockeditems.use")) {
				PlayerUtils.noPermissionsMessage(player);
				return true;
			}
			final ItemStack hand = player.getInventory().getItemInHand();
			if (ItemStackUtils.isNull(hand)) {
				PlayerUtils.errorMessage(player,
						"&cHey sir , the item cannot be null or air , please held real item in your hand in another once !");
				return true;
			}

			player.getInventory().setItemInHand(null);
			Bukkit.getScheduler().runTaskLater(instance,() -> {
				player.getInventory().setItemInHand(ItemStackUtils.createLockedItem(hand));
				PlayerUtils.successfullyMessage(player, "&aYou have been locked this item successfully !");
			},20L);

			return true;
		});
	}

}