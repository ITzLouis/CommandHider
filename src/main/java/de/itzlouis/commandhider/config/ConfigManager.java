package de.itzlouis.commandhider.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigManager {

    public File folder = new File("plugins/CommandHider");
    public File MessageFile = new File("plugins/CommandHider/Message.yml");
    public File ConfigFile = new File("plugins/CommandHider/Config.yml");

    public YamlConfiguration message = YamlConfiguration.loadConfiguration(MessageFile);
    public YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);

    public void loadConfiguration() {
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!ConfigFile.exists()) {
            try {
                ConfigFile.createNewFile();
                config.options().header("ItzLouis#9999 | CommandHider.java | https://itzlouis.de/commandhider");
                config.options().copyDefaults(true);
                config.addDefault("Permissions","CommandHider.Command.Use");
                config.options().copyHeader(true);
                config.save(ConfigFile);

                FileInputStream fis = new FileInputStream(ConfigFile);
                config.load(new InputStreamReader(fis, StandardCharsets.UTF_8));

            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        if (!MessageFile.exists()) {
            try {
                MessageFile.createNewFile();
                message.options().header("ItzLouis#9999 | CommandHider.java | https://itzlouis.de/commandhider");
                message.options().copyDefaults(true);
                message.addDefault("Message.Prefix","&8[&cNoRights§8] ");
                message.addDefault("Message.CommandUse.NoRights","&&7This command &ccould not &7be found");
                message.options().copyHeader(true);
                message.save(MessageFile);
                FileInputStream fis = new FileInputStream(MessageFile);
                message.load(new InputStreamReader(fis, StandardCharsets.UTF_8));

            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

}