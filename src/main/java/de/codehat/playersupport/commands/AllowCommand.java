/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.commands;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AllowCommand extends BaseCommand {

    public AllowCommand(PlayerSupport plugin, LanguageHandler lang) {
        super(plugin, lang);
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("playersupport.allowplayer")) {
            Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
            return;
        }
        if (args.length == 1) {
            Message.sendLogoMsg(sender, "/support allow [player]");
            return;
        }
        @SuppressWarnings("deprecation")
        OfflinePlayer p = this.plugin.getServer().getOfflinePlayer(args[1]);
        if (!this.plugin.getConfig().getList("deniedPlayers").contains(p.getUniqueId().toString())) {
            Message.sendLogoMsg(sender, lang.getLang("playernotinlist"));
            return;
        }
        @SuppressWarnings("unchecked")
        List<String> deniedPlayers = (List<String>) this.plugin.getConfig().getList("deniedPlayers");
        deniedPlayers.remove(p.getUniqueId().toString());
        this.plugin.getConfig().set("deniedPlayers", deniedPlayers);
        this.plugin.saveConfig();
        Message.sendLogoMsg(sender, String.format(lang.getLang("playerremove"), p.getName()));
    }

}
