/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport;

import de.codehat.playersupport.commands.*;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.listener.ChatListener;
import de.codehat.playersupport.updater.UpdateResult;
import de.codehat.playersupport.updater.Updater;
import de.codehat.playersupport.util.Message;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PlayerSupport extends JavaPlugin implements Listener {

    //The language files.
    public File en = new File("plugins" + File.separator + "PlayerSupport" + File.separator + "languages" + File.separator
            + "en.yml");
    public File de = new File("plugins" + File.separator + "PlayerSupport" + File.separator + "languages" + File.separator
            + "de.yml");

    //The language FileConfigurations.
    public FileConfiguration cfgen = YamlConfiguration.loadConfiguration(en);
    public FileConfiguration cfgde = YamlConfiguration.loadConfiguration(de);

    //Updater Strings.
    public String updateLink = null, updateVersion = null;

    //Support player HashMap.
    public Map<Player, Player> support_players = new HashMap<>();

    //Checks which language is loaded.
    public boolean languageEN;

    //Checks if sending an updatemessage is allowed.
    public boolean updatePlayerMsg;

    //Minecraft logger.
    public Logger log = Logger.getLogger("Minecraft");

    //The language module.
    private LanguageHandler lang = new LanguageHandler(this);

    @Override
    public void onDisable() {
        saveConfig();
        PluginDescriptionFile plugin = getDescription();
        log.info("Version " + plugin.getVersion() + " by CodeHat disabled.");
        super.onDisable();
    }

    @Override
    public void onEnable() {
        this.log = this.getLogger();
        loadConfig();
        lang.languageFileEN();
        lang.languageFileDE();
        lang.loadLanguages();
        Message.lang = lang;
        new ChatListener(this, lang);
        registerCommands();
        checkUpdates();
        PluginDescriptionFile plugin = getDescription();
        log.info("Version " + plugin.getVersion() + " by CodeHat enabled.");
        super.onEnable();
    }

    /**
     * Registers all commands.
     */
    private void registerCommands() {
        CommandHandler cmdh = new CommandHandler(this, lang);
        cmdh.registerNewCommand("help", new HelpCommand(this, lang));
        cmdh.registerNewCommand("info", new InfoCommand(this, lang));
        cmdh.registerNewCommand("reload", new ReloadConfigCommand(this, lang));
        cmdh.registerNewCommand("add", new SupAddCommand(this, lang));
        cmdh.registerNewCommand("remove", new SupRemoveCommand(this, lang));
        cmdh.registerNewCommand("list", new ListCommand(this, lang));
        cmdh.registerNewCommand("player", new SupPlayerCommand(this, lang));
        cmdh.registerNewCommand("deny", new DenyCommand(this, lang));
        cmdh.registerNewCommand("allow", new AllowCommand(this, lang));
        getCommand("support").setExecutor(new CommandHandler(this, lang));
    }

    /**
     * Loads the config file.
     */
    public void loadConfig() {
        if (new File("plugins" + File.separator + "PlayerSupport" + File.separator + "config.yml").exists()) {
            FileConfiguration cfg = this.getConfig();
            cfg.options().copyDefaults(true);
        } else {
            saveDefaultConfig();
            FileConfiguration cfg = this.getConfig();
            cfg.options().copyDefaults(true);
        }
    }

    /**
     * Checks for plugin updates.
     */
    public void checkUpdates() {
        if (getConfig().getBoolean("updatecheck")) {
            final PluginDescriptionFile plugin = getDescription();
            log.info("Checking for Updates...");
            this.getServer().getScheduler().runTaskAsynchronously(this, new Updater(plugin.getVersion(),
                    (result, version) -> {
                        if (result == UpdateResult.NEEDED) {
                            log.info("A new version is available: v" + version);
                            log.info("Get it from: " + Updater.getDownloadUrl());
                            updatePlayerMsg = true;
                            updateLink = Updater.getDownloadUrl();
                            updateVersion = version;
                        } else if (result == UpdateResult.UNNEEDED) {
                            log.info("No new version available");
                        } else {
                            log.info("Could not check for Updates");
                        }
                    }));
        }
    }
}