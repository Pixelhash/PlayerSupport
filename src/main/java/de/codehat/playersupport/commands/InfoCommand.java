/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;

public class InfoCommand extends BaseCommand {

	public InfoCommand(PlayerSupport plugin, LanguageHandler lang) {
		super(plugin, lang);
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Info command
		if (!sender.hasPermission("playersupport.info")) {
			Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
			return;
		}
		Message.sendMsg(sender, "&6+------------------&c[&ePlayerSupport&c]&6------------------+");
		Message.sendMsg(sender, lang.getLang("author") + " &cCodeHat");
		Message.sendMsg(sender, "&6Version: &c" + this.plugin.getDescription().getVersion());
		Message.sendMsg(sender, lang.getLang("cmd") + " " + lang.getLang("cmdhelp"));
		Message.sendMsg(sender, "&6+--------------------------------------------------+");
	}
}