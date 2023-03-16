package me.sanhak.lockyouritem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.sanhak.lockyouritem.main.Main;
import me.sanhak.lockyouritem.methods.ItemCreator;
import me.sanhak.lockyouritem.utils.ItemStackUtils;
import me.sanhak.lockyouritem.utils.PlayerUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

import org.bukkit.command.CommandExecutor;

public class LockCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(StringUtils.format("&cSorry , only players can perform this command !"));
			return false;
		}

		Player player = (Player) commandSender;

		if (player.hasPermission("lockeditems.use")) {
			if (args.length == 0) {
				ItemStack itemInPlayerHand = player.getItemInHand();
				if (!ItemStackUtils.isNull(itemInPlayerHand)) {
					player.setItemInHand(null);
					new BukkitRunnable() {

						@Override
						public void run() {
							player.setItemInHand(ItemCreator.createLockedItem(itemInPlayerHand));
							PlayerUtils.successfullyMessage(player, "&aYou have been locked this item successfully !");

						}
					}.runTaskLater(Main.getInstance(), 20);

				} else {
					PlayerUtils.errorMessage(player,
							"&cHey sir , the item cannot be null or air , please held real item in your hand in another once !");
				}

			} else {
				player.sendMessage(StringUtils.format(
						"&cWrong use sir , please try to write command like this &f&L/lock &cand held item in your hand"));
			}
		} else {
			PlayerUtils.noPermissionsMessage(player);
		}

		return true;
	}

}