package me.sanhak.lockyouritem.commands;

import org.bukkit.entity.Player;

import lombok.NonNull;
import me.sanhak.lockyouritem.main.Main;
import me.sanhak.lockyouritem.utils.PlayerUtils;
import me.sanhak.lockyouritem.utils.StringUtils;

public final class ReloadCommand {

    public ReloadCommand(@NonNull Main instance) {
        instance.getCommand("lockrl").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(StringUtils.format("&cSorry , only players can perform this command !"));
                return true;
            }
            final Player player = (Player) sender;
            if (!player.hasPermission("lockeditems.admin")) {
                PlayerUtils.noPermissionsMessage(player);
                return true;
            }

            instance.reloadConfig();
            PlayerUtils.successfullyMessage(player, "&aYou have been reloaded configuration file succesfully !");

            return true;
        });

    }

}
