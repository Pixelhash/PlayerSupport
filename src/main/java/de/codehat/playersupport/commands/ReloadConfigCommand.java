/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.commands;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * PlayerSupport
 *
 * @author CodeHat
 */

public class ReloadConfigCommand extends BaseCommand {

    public ReloadConfigCommand(PlayerSupport plugin, LanguageHandler lang) {
        super(plugin, lang);
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("playersupport.reload")) {
            Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
            return;
        }
        this.plugin.reloadConfig();
        this.plugin.cfgen = YamlConfiguration.loadConfiguration(this.plugin.en);
        this.plugin.cfgde = YamlConfiguration.loadConfiguration(this.plugin.de);
        this.lang.loadLanguages();
        Message.sendLogoMsg(sender, lang.getLang("configreload"));
    }
}