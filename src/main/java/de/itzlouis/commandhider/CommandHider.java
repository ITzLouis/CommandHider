package de.itzlouis.commandhider;

import de.itzlouis.commandhider.commands.CommandHiderCommand;
import de.itzlouis.commandhider.config.ConfigManager;
import de.itzlouis.commandhider.listener.PlayerCommandPreprocessListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CommandHider extends JavaPlugin {

    private static CommandHider instance;
    private static ConfigManager configManager;
    public static String Prefix;
    public static List<String> commands = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        configManager.loadConfiguration();
        Prefix = configManager.message.getString("Message.Prefix").replace("&", "§");
        commands = configManager.config.getStringList("List.Commands");

        getCommand("commandhider").setExecutor(new CommandHiderCommand());
        registerListener();
        fillWords();

        Bukkit.getConsoleSender().sendMessage("§8[§aONLINE§8] §7CommandHinder started successfully");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8[§cOFFLINE§8] §7CommandHinder stopped successfully");
    }

    public void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerCommandPreprocessListener(), CommandHider.getInstance());
    }

    public void fillWords() {
        String[] array = new String[]{"pl", "?", "about", "ver", "bukkit", "version", "bukkit:version","plugins","bukkit:pl","bukkit:plugins","bukkit:about","bukkit:ver"};
        List<String> strings = Arrays.asList(array);
        commands.addAll(strings);
        configManager.config.set("List.Commands", commands);
        try {
            configManager.config.save(configManager.ConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static CommandHider getInstance() {
        return instance;
    }
}
