/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.listener;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * PlayerSupport
 *
 * @author CodeHat
 */

public class ChatListener implements Listener {

    //Instances
    private PlayerSupport plugin;
    private LanguageHandler lang;

    /**
     * Constructor.
     *
     * @param instance PlayerSupport instance.
     * @param lang     LanguageHandler instance.
     */
    public ChatListener(PlayerSupport instance, LanguageHandler lang) {
        this.plugin = instance;
        this.lang = lang;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWritingMessage(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (this.plugin.support_players.containsKey(p) && this.plugin.support_players.get(p) != null) {
            if (e.getMessage().equalsIgnoreCase("EXIT")) {
                Message.sendMsg(p, lang.getLang("exitchatself"));
                Message.sendMsg(this.plugin.support_players.get(p), String.format(lang.getLang("exitchat"), p.getName()));
                this.plugin.support_players.remove(p);
            } else {
                Message.sendMsg(p, String.format(lang.getLang("playertosupmsg"), p.getName(), e.getMessage()));
                Message.sendMsg(this.plugin.support_players.get(p), String.format(lang.getLang("playertosupmsg"), p.getName(), e.getMessage()));
            }
            e.setCancelled(true);
        } else if (this.plugin.support_players.containsValue(p)) {
            for (Player help : this.plugin.support_players.keySet()) {
                if (this.plugin.support_players.get(help).equals(p)) {
                    if (e.getMessage().equalsIgnoreCase("EXIT")) {
                        Message.sendMsg(p, lang.getLang("exitchatself"));
                        Message.sendMsg(help, String.format(lang.getLang("exitchat"), p.getName()));
                        this.plugin.support_players.remove(help);
                    } else {
                        Message.sendMsg(p, String.format(lang.getLang("suptoplayermsg"), p.getName(), e.getMessage()));
                        Message.sendMsg(help, String.format(lang.getLang("suptoplayermsg"), p.getName(), e.getMessage()));
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

    /**
     * Sends the updatemessage on server join to players with permission.
     *
     * @param e PlayerJoinEvent.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (this.plugin.updatePlayerMsg && p.hasPermission("playersupport.updatemsg")) {
            Message.sendMsg(p, lang.getLang("updatemsg") + " (" + this.plugin.updateVersion + "):");
            Message.sendMsg(p, "&2" + this.plugin.updateLink);
        }
        if (this.plugin.getConfig().getList("supporters").contains(p.getUniqueId()) && !this.plugin.support_players.isEmpty()) {
            Message.sendLogoMsg(p, String.format(lang.getLang("joinhelpmsg"), String.valueOf(this.plugin.support_players.size())));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (this.plugin.support_players.containsKey(p)) {
            this.plugin.support_players.remove(p);
        }
    }
}