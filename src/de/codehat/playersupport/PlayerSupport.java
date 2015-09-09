/*
 * Copyright (c) 2015 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport;

import de.codehat.metrics.Metrics;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PlayerSupport extends JavaPlugin implements Listener {

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

	//The language files.
	public final File en = new File("plugins" + File.separator + "PlayerSupport" + File.separator + "languages" + File.separator
			+ "en.yml");
	public final File de = new File("plugins" + File.separator + "PlayerSupport" + File.separator + "languages" + File.separator
			+ "de.yml");

	//The language FileConfigurations.
	public final FileConfiguration cfgen = YamlConfiguration.loadConfiguration(en);
	public final FileConfiguration cfgde = YamlConfiguration.loadConfiguration(de);

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
		startMetrics();
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
	 * Starts metrics service.
	 */
	public void startMetrics() {
		if(this.getConfig().getBoolean("metrics")) {
			Metrics metrics;
			try {
				metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				log.info("Metrics: Failed to submit the stats :-(");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks for plugin updates.
	 */
	public void checkUpdates() {
		if(getConfig().getBoolean("updatecheck")) {
			final PluginDescriptionFile plugin = getDescription();
			this.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
				@Override
				public void run() {
					Updater updater = new Updater(plugin.getVersion());
					UpdateResult result = updater.checkForUpdate();
					log.info("Checking for Updates...");
					if (result == UpdateResult.NEEDED) {
						log.info("A new version is available: v" + updater.getLatestVersion());
						log.info("Get it from: " + updater.getDownloadUrl());
						updatePlayerMsg = true;
						updateLink = updater.getDownloadUrl();
						updateVersion = updater.getLatestVersion();
					} else if (result == UpdateResult.UNNEEDED){
						log.info("No new version available");
					} else {
						log.info("Could not check for Updates");
					}
				}
			});
		}
	}
}