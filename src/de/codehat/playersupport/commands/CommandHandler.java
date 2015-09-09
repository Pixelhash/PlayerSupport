package de.codehat.playersupport.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.codehat.playersupport.PlayerSupport;
import de.codehat.playersupport.languages.LanguageHandler;
import de.codehat.playersupport.util.Message;

/**
 * PlayerSupport
 * @author CodeHat
 */

public class CommandHandler implements CommandExecutor {

	private static HashMap<String, BaseCommand> registry = new HashMap<>();

	private PlayerSupport plugin;
	private LanguageHandler lang;

	public CommandHandler(PlayerSupport plugin, LanguageHandler lang) {
		this.plugin = plugin;
		this.lang = lang;
	}

	public void registerNewCommand(String name, BaseCommand cmd){
		registry.put(name, cmd);
	}

	public boolean exists(String name){
		return registry.containsKey(name);
	}

	public BaseCommand getExecutor(String name){
		return registry.get(name);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				Message.sendLogoMsg(sender, lang.getLang("notplayer"));
				return true;
			}
			if (!sender.hasPermission("playersupport.support")) {
				Message.sendLogoMsg(sender, lang.getLang("nocmdaccess"));
				return true;
			}
			Player p = (Player) sender;
			if (this.plugin.getConfig().getList("supporters").contains(p.getUniqueId().toString())) {
				this.getExecutor("list").onCommand(p, cmd, label, args);
				return true;
			}
			if (this.plugin.getConfig().getList("deniedPlayers").contains(p.getUniqueId().toString())) {
				Message.sendLogoMsg(p, lang.getLang("denymsg"));
				return true;
			}
			if (this.plugin.support_players.containsKey(p)) {
				this.plugin.support_players.remove(p);
				Message.sendLogoMsg(p, lang.getLang("requestrevoke"));
				return true;
			}
			this.plugin.support_players.put(p, null);
			Message.sendLogoMsg(p, lang.getLang("supsearch"));
			int supCount = 0;
			for (Object uuid : this.plugin.getConfig().getList("supporters")) {
				Player sup = Bukkit.getPlayer(UUID.fromString((String) uuid));
				if (sup != null && sup.isOnline()) {
					Message.sendLogoMsg(sup, String.format(lang.getLang("suphelp"), p.getName()));
					supCount++;
				}
			}
			if (supCount == 0) {
				Message.sendLogoMsg(p, lang.getLang("nosuponline"));
			} else {
				Message.sendLogoMsg(p, String.format(lang.getLang("supcountmsg"), String.valueOf(supCount)));
			}
			return true;
		}
		//Rest of commands
		if (exists(args[0])) {
			getExecutor(args[0]).onCommand(sender, cmd, label, args);
			return true;
		} else {
			Message.sendLogoMsg(sender, lang.getLang("unknowncmd"));
			Message.sendLogoMsg(sender, lang.getLang("unknowncmdhelp"));
			return true;
		}
	}
}