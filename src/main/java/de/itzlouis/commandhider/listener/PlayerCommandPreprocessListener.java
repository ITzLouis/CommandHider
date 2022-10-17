package de.itzlouis.commandhider.listener;

import de.itzlouis.commandhider.CommandHider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String Prefix = CommandHider.Prefix;
        String[] command = event.getMessage().toLowerCase().split(" ", 2);
        CommandHider.commands.forEach(commands -> {
            if (player.hasPermission("CommandHider.Command.Use")) {
                if (command[0].equalsIgnoreCase("/" + commands.toLowerCase())) {
                    event.setCancelled(true);
                    player.sendMessage(Prefix + CommandHider.getConfigManager().message.getString("Message.CommandUse.NoRights").replace("&", "§"));
                }
            }
        });
    }
}
