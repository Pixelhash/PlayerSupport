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

public class SupAddCommand extends BaseCommand {

    public SupAddCommand(PlayerSupport plugin, LanguageHandler lang) {
        super(plugin, lang);
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("playersupport.addsup")) {
            Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
            return;
        }
        if (args.length == 1) {
            Message.sendLogoMsg(sender, "/support add [player]");
            return;
        }
        @SuppressWarnings("deprecation")
        OfflinePlayer p = this.plugin.getServer().getOfflinePlayer(args[1]);
        if (this.plugin.getConfig().getList("supporters").contains(p.getUniqueId().toString())) {
            Message.sendLogoMsg(sender, lang.getLang("alreadyadded"));
            return;
        }
        @SuppressWarnings("unchecked")
        List<String> supporters = (List<String>) this.plugin.getConfig().getList("supporters");
        supporters.add(p.getUniqueId().toString());
        this.plugin.getConfig().set("supporters", supporters);
        this.plugin.saveConfig();
        Message.sendLogoMsg(sender, String.format(lang.getLang("addlist"), p.getName()));
    }

}
