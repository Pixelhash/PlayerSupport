package de.codehat.playersupport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;

public class ListCommand extends BaseCommand {

	public ListCommand(PlayerSupport plugin, LanguageHandler lang) {
		super(plugin, lang);
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("playersupport.list")) {
			Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
			return;
		}
		Message.sendLogoMsg(sender, lang.getLang("playerlist"));
		for (Player p : this.plugin.support_players.keySet()) {
			if (this.plugin.support_players.get(p) == null) {
				Message.sendMsg(sender, "&7- &2" + p.getName());
			} else {
				Message.sendMsg(sender, String.format(lang.getLang("helpedplayerinlist"), p.getName(),this.plugin.support_players.get(p).getName()));
			}
		}
	}

}
