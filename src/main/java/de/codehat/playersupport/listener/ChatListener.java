/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.listener;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * PlayerSupport
 * @author CodeHat
 */

public class ChatListener implements Listener {

	//Instances
	private PlayerSupport plugin;
	private LanguageHandler lang;

	/**
	 * Constructor.
	 * @param instance PlayerSupport instance.
	 * @param lang LanguageHandler instance.
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
	 * @param e PlayerJoinEvent.
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		//TODO: Update msg perm!
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

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPickup(PlayerPickupItemEvent e) {
		Item i = e.getItem();
		Player p = e.getPlayer();
		/*if (i.hasMetadata("player")) {
			if (!i.getMetadata("player").get(0).toString().equals(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}*/
		if (i.getItemStack().getItemMeta().hasLore()) {
			if (!i.getItemStack().getItemMeta().getLore().get(0).equals(p.getUniqueId().toString())) {
				e.setCancelled(true);
				this.plugin.log.info("CANCEL");
			}
		}
	}

	//TODO: https://www.spigotmc.org/threads/saving-data-in-a-block.126605/#post-1344403
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		Collection<ItemStack> drops = b.getDrops();
		for (ItemStack is : drops) {
			List<String> lore = new ArrayList<>();
			ItemMeta im = is.getItemMeta();
			if (is.getType() == Material.DIRT) {
				lore.add("bla-bla-bla-bla");
				this.plugin.log.info("DIRT");
				im.setLore(lore);
			} else {
				lore.add(p.getUniqueId().toString());
				im.setLore(lore);
				this.plugin.log.info("PLAYER");
			}
			is.setItemMeta(im);
			b.getWorld().dropItemNaturally(b.getLocation(), is);
		}
		b.setType(Material.AIR);
		e.setCancelled(true);
		/*if (b.getType() == Material.GRASS) {
			b.setMetadata("player", new FixedMetadataValue(this.plugin, "bla-bla-bla-bla"));
		} else {
			b.setMetadata("player", new FixedMetadataValue(this.plugin, p.getUniqueId()));
		}*/
	}
}