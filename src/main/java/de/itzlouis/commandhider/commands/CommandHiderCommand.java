package de.itzlouis.commandhider.commands;

import de.itzlouis.commandhider.CommandHider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CommandHiderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("§cYou must be a player to use this");
            return false;
        }
            Player player = (Player) sender;
            String Prefix = "§cCommandHider §7• ";
            if (player.hasPermission("CommandHider.Command.Use")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args.length > 1) {
                            String commandName = args[1];
                            if (!CommandHider.commands.contains(commandName)) {
                                CommandHider.commands.add(commandName);
                                CommandHider.getConfigManager().config.set("List.Commands", CommandHider.commands);
                                try {
                                    CommandHider.getConfigManager().config.save(CommandHider.getConfigManager().ConfigFile);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                player.sendMessage(Prefix + "§7The command was §asuccessfully §7hint to the list");
                            } else {
                                player.sendMessage(Prefix + "§7The command is §calready §7in the list");
                            }
                        } else {
                            player.sendMessage(Prefix + "§7Usage: /CommandHider add <name>");
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (args.length > 1) {
                            String commandName = args[1];
                            if (CommandHider.commands.contains(commandName)) {
                                CommandHider.commands.remove(commandName);
                                CommandHider.getConfigManager().config.set("List.Commands", CommandHider.commands);
                                try {
                                    CommandHider.getConfigManager().config.save(CommandHider.getConfigManager().ConfigFile);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                player.sendMessage(Prefix + "§7The command was §asuccessfully §7remove from the list");
                            } else {
                                player.sendMessage(Prefix + "§7Command §ccould not §7be found");
                            }
                        } else {
                            player.sendMessage(Prefix + "§7Usage: /CommandHider remove <name>");
                        }
                    } else if (args[0].equalsIgnoreCase("list")) {

                    }
                } else {
                    player.sendMessage(Prefix + "§7Usage: /CommandHider <add/remove/list>");
                }
            } else {
                player.sendMessage(Prefix + "§7You have §cno rights §7to do this");
            }
        return false;
    }
}
