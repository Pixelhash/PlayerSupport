/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.commands;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * PlayerSupport
 *
 * @author CodeHat
 */

public abstract class BaseCommand {

    protected PlayerSupport plugin;
    protected LanguageHandler lang;

    public BaseCommand(PlayerSupport plugin, LanguageHandler lang) {
        this.plugin = plugin;
        this.lang = lang;
    }

    public abstract void onCommand(CommandSender sender, Command cmd, String label, String[] args);
}