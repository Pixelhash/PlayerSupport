/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;

public class SupPlayerCommand extends BaseCommand {

	public SupPlayerCommand(PlayerSupport plugin, LanguageHandler lang) {
		super(plugin, lang);
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Message.sendLogoMsg(sender, lang.getLang("notplayer"));
			return;
		}
		if (!sender.hasPermission("playersupport.supplayer")) {
			Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
			return;
		}
		Player p = (Player) sender;
		if (args.length == 1) {
			Message.sendLogoMsg(p, "/support player [name]");
			return;
		}
		for (Player sup : this.plugin.support_players.values()) {
			if (sup != null) {
				Message.sendLogoMsg(sup, lang.getLang("alreadyhelping"));
				return;
			}
		}
		Player help = Bukkit.getPlayerExact(args[1]);
		if (help == null || !this.plugin.support_players.containsKey(help)) {
			Message.sendLogoMsg(p, lang.getLang("playernotonlinenohelp"));
			return;
		}
		if (help == p) {
			Message.sendLogoMsg(p, "&cYou cannot help yourself...");
			return;
		}
		if (this.plugin.support_players.get(help) != null) {
			Message.sendLogoMsg(p, lang.getLang("alreadyhelped"));
			return;
		}
		this.plugin.support_players.put(help, p);
		Message.sendLogoMsg(p, String.format(lang.getLang("helpmsg"), help.getName()));
		Message.sendLogoMsg(p, lang.getLang("helpchatmsg"));
		Message.sendLogoMsg(p, lang.getLang("exitchatinfo"));
		Message.sendLogoMsg(help, String.format(lang.getLang("gethelpmsg"), p.getName()));
		Message.sendLogoMsg(help, lang.getLang("helpchatmsg"));
		Message.sendLogoMsg(help, lang.getLang("exitchatinfo"));
	}

}
