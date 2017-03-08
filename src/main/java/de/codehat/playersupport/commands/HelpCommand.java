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

/**
 * PlayerSupport
 *
 * @author CodeHat
 */

public class HelpCommand extends BaseCommand {

    public HelpCommand(PlayerSupport plugin, LanguageHandler lang) {
        super(plugin, lang);
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("playersupport.help")) {
            Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
            return;
        }
        Message.sendMsg(sender, "&6+------------------&e[&cPlayerSupport&e]&6------------------+");
        Message.sendMsg(sender, "&6/sup &a--- " + lang.getLang("helpsup"));
        Message.sendMsg(sender, "&6/sup list &a--- " + lang.getLang("helplist"));
        Message.sendMsg(sender, "&6/sup player [player] &a--- " + lang.getLang("helpaccept"));
        Message.sendMsg(sender, "&6/sup add [player] &a--- " + lang.getLang("helpsupadd"));
        Message.sendMsg(sender, "&6/sup remove [player] &a--- " + lang.getLang("helpsupremove"));
        Message.sendMsg(sender, "&6/sup allow [player] &a--- " + lang.getLang("helpallow"));
        Message.sendMsg(sender, "&6/sup deny [player] &a--- " + lang.getLang("helpdeny"));
        Message.sendMsg(sender, "&6/sup reload &a--- " + lang.getLang("configreloadhelp"));
        Message.sendMsg(sender, "&6/sup help &a--- " + lang.getLang("cmdlist"));
        Message.sendMsg(sender, "&6/sup info &a--- " + lang.getLang("info"));
        Message.sendMsg(sender, "&6+--------------------------------------------------+");
        return;
    }

}