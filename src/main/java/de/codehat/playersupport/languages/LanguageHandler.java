/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.languages;

import java.io.IOException;
import java.util.HashMap;

import de.codehat.playersupport.PlayerSupport;

/**
 * PlayerSupport
 * @author CodeHat
 */

public class LanguageHandler {

	//PlayerSupport instance.
	private PlayerSupport plugin;

	//Language HashMap.
	public HashMap<String, String> lg;

	/**
	 * Constructor.
	 * @param instance SignColors instance.
	 */
	public LanguageHandler(PlayerSupport instance) {
		this.plugin = instance;
	}

	/**
	 * Loads the language HashMap (en/de).
	 */
	public void loadLanguages() {
		this.plugin.languageEN = this.plugin.getConfig().getString("language").equalsIgnoreCase("en");
		if (this.plugin.languageEN) {
			lg = new HashMap<>();
			lg.put("tag", this.plugin.cfgen.getString("EN.TAG"));
			lg.put("nocmdaccess", this.plugin.cfgen.getString("EN.NOCMDACCESS"));
			lg.put("noaction", this.plugin.cfgen.getString("EN.NOACTION"));
			lg.put("author", this.plugin.cfgen.getString("EN.AUTHOR"));
			lg.put("cmd", this.plugin.cfgen.getString("EN.CMD"));
			lg.put("cmdhelp", this.plugin.cfgen.getString("EN.CMDHELP"));
			lg.put("toomanyargs", this.plugin.cfgen.getString("EN.TOOMANYARGS"));
			lg.put("unknowncmd", this.plugin.cfgen.getString("EN.UNKNOWNCMD"));
			lg.put("unknowncmdhelp", this.plugin.cfgen.getString("EN.UNKNOWNCMDHELP"));
			lg.put("configreload", this.plugin.cfgen.getString("EN.CONFIGRELOAD"));
			lg.put("info", this.plugin.cfgen.getString("EN.INFO"));
			lg.put("configreloadhelp", this.plugin.cfgen.getString("EN.CONFIGRELOADHELP"));
			lg.put("cmdlist", this.plugin.cfgen.getString("EN.CMDLIST"));
			lg.put("updatemsg", this.plugin.cfgen.getString("EN.UPDATEMSG"));
			lg.put("playernotonline", this.plugin.cfgen.getString("EN.PLAYERNOTONLINE"));
			lg.put("playernotonlinenohelp", this.plugin.cfgen.getString("EN.PLAYERNOTONLINENOHELP"));
			lg.put("playernotinlist", this.plugin.cfgen.getString("EN.PLAYERNOTINLIST"));
			lg.put("playerremove", this.plugin.cfgen.getString("EN.PLAYERREMOVE"));
			lg.put("notplayer", this.plugin.cfgen.getString("EN.NOTPLAYER"));
			lg.put("denymsg", this.plugin.cfgen.getString("EN.DENYMSG"));
			lg.put("requestrevoke", this.plugin.cfgen.getString("EN.REQUESTREVOKE"));
			lg.put("supsearch", this.plugin.cfgen.getString("EN.SUPSEARCH"));
			lg.put("suphelp", this.plugin.cfgen.getString("EN.SUPHELP"));
			lg.put("nosuponline", this.plugin.cfgen.getString("EN.NOSUPONLINE"));
			lg.put("supcountmsg", this.plugin.cfgen.getString("EN.SUPCOUNTMSG"));
			lg.put("alreadyadded", this.plugin.cfgen.getString("EN.ALREADYADDED"));
			lg.put("addlist", this.plugin.cfgen.getString("EN.ADDLIST"));
			lg.put("playerlist", this.plugin.cfgen.getString("EN.PLAYERLIST"));
			lg.put("helpedplayerinlist", this.plugin.cfgen.getString("EN.HELPEDPLAYERINLIST"));
			lg.put("helpsup", this.plugin.cfgen.getString("EN.HELPSUP"));
			lg.put("helplist", this.plugin.cfgen.getString("EN.HELPLIST"));
			lg.put("helpaccept", this.plugin.cfgen.getString("EN.HELPACCEPT"));
			lg.put("helpsupadd", this.plugin.cfgen.getString("EN.HELPSUPADD"));
			lg.put("helpsupremove", this.plugin.cfgen.getString("EN.HELPSUPREMOVE"));
			lg.put("helpallow", this.plugin.cfgen.getString("EN.HELPALLOW"));
			lg.put("helpdeny", this.plugin.cfgen.getString("EN.HELPDENY"));
			lg.put("joinhelpmsg", this.plugin.cfgen.getString("EN.JOINHELPMSG"));
			lg.put("playertosupmsg", this.plugin.cfgen.getString("EN.PLAYERTOSUPMSG"));
			lg.put("suptoplayermsg", this.plugin.cfgen.getString("EN.SUPTOPLAYERMSG"));
			lg.put("alreadyhelped", this.plugin.cfgen.getString("EN.ALREADYHELPED"));
			lg.put("helpmsg", this.plugin.cfgen.getString("EN.HELPMSG"));
			lg.put("helpchatmsg", this.plugin.cfgen.getString("EN.HELPCHATMSG"));
			lg.put("gethelpmsg", this.plugin.cfgen.getString("EN.GETHELPMSG"));
			lg.put("exitchat", this.plugin.cfgen.getString("EN.EXITCHAT"));
			lg.put("exitchatinfo", this.plugin.cfgen.getString("EN.EXITCHATINFO"));
			lg.put("exitchatself", this.plugin.cfgen.getString("EN.EXITCHATSELF"));
			lg.put("alreadyhelping", this.plugin.cfgen.getString("EN.ALREADYHELPING"));
		} else {
			lg = new HashMap<>();
			lg.put("tag", this.plugin.cfgde.getString("DE.TAG"));
			lg.put("nocmdaccess", this.plugin.cfgde.getString("DE.NOCMDACCESS"));
			lg.put("noaction", this.plugin.cfgde.getString("DE.NOACTION"));
			lg.put("author", this.plugin.cfgde.getString("DE.AUTHOR"));
			lg.put("cmd", this.plugin.cfgde.getString("DE.CMD"));
			lg.put("cmdhelp", this.plugin.cfgde.getString("DE.CMDHELP"));
			lg.put("toomanyargs", this.plugin.cfgde.getString("DE.TOOMANYARGS"));
			lg.put("unknowncmd", this.plugin.cfgde.getString("DE.UNKNOWNCMD"));
			lg.put("unknowncmdhelp", this.plugin.cfgde.getString("DE.UNKNOWNCMDHELP"));
			lg.put("configreload", this.plugin.cfgde.getString("DE.CONFIGRELOAD"));
			lg.put("info", this.plugin.cfgde.getString("DE.INFO"));
			lg.put("configreloadhelp", this.plugin.cfgde.getString("DE.CONFIGRELOADHELP"));
			lg.put("cmdlist", this.plugin.cfgde.getString("DE.CMDLIST"));
			lg.put("updatemsg", this.plugin.cfgde.getString("DE.UPDATEMSG"));
			lg.put("playernotonline", this.plugin.cfgde.getString("DE.PLAYERNOTONLINE"));
			lg.put("playernotonlinenohelp", this.plugin.cfgde.getString("DE.PLAYERNOTONLINENOHELP"));
			lg.put("playernotinlist", this.plugin.cfgde.getString("DE.PLAYERNOTINLIST"));
			lg.put("playerremove", this.plugin.cfgde.getString("DE.PLAYERREMOVE"));
			lg.put("notplayer", this.plugin.cfgde.getString("DE.NOTPLAYER"));
			lg.put("denymsg", this.plugin.cfgde.getString("DE.DENYMSG"));
			lg.put("requestrevoke", this.plugin.cfgde.getString("DE.REQUESTREVOKE"));
			lg.put("supsearch", this.plugin.cfgde.getString("DE.SUPSEARCH"));
			lg.put("suphelp", this.plugin.cfgde.getString("DE.SUPHELP"));
			lg.put("nosuponline", this.plugin.cfgde.getString("DE.NOSUPONLINE"));
			lg.put("supcountmsg", this.plugin.cfgde.getString("DE.SUPCOUNTMSG"));
			lg.put("alreadyadded", this.plugin.cfgde.getString("DE.ALREADYADDED"));
			lg.put("addlist", this.plugin.cfgde.getString("DE.ADDLIST"));
			lg.put("playerlist", this.plugin.cfgde.getString("DE.PLAYERLIST"));
			lg.put("helpedplayerinlist", this.plugin.cfgde.getString("DE.HELPEDPLAYERINLIST"));
			lg.put("helpsup", this.plugin.cfgde.getString("DE.HELPSUP"));
			lg.put("helplist", this.plugin.cfgde.getString("DE.HELPLIST"));
			lg.put("helpaccept", this.plugin.cfgde.getString("DE.HELPACCEPT"));
			lg.put("helpsupadd", this.plugin.cfgde.getString("DE.HELPSUPADD"));
			lg.put("helpsupremove", this.plugin.cfgde.getString("DE.HELPSUPREMOVE"));
			lg.put("helpallow", this.plugin.cfgde.getString("DE.HELPALLOW"));
			lg.put("helpdeny", this.plugin.cfgde.getString("DE.HELPDENY"));
			lg.put("joinhelpmsg", this.plugin.cfgde.getString("DE.JOINHELPMSG"));
			lg.put("playertosupmsg", this.plugin.cfgde.getString("DE.PLAYERTOSUPMSG"));
			lg.put("suptoplayermsg", this.plugin.cfgde.getString("DE.SUPTOPLAYERMSG"));
			lg.put("alreadyhelped", this.plugin.cfgde.getString("DE.ALREADYHELPED"));
			lg.put("helpmsg", this.plugin.cfgde.getString("DE.HELPMSG"));
			lg.put("helpchatmsg", this.plugin.cfgde.getString("DE.HELPCHATMSG"));
			lg.put("gethelpmsg", this.plugin.cfgde.getString("DE.GETHELPMSG"));
			lg.put("exitchat", this.plugin.cfgde.getString("DE.EXITCHAT"));
			lg.put("exitchatinfo", this.plugin.cfgde.getString("DE.EXITCHATINFO"));
			lg.put("exitchatself", this.plugin.cfgde.getString("DE.EXITCHATSELF"));
			lg.put("alreadyhelping", this.plugin.cfgde.getString("DE.ALREADYHELPING"));
		}
	}

	/**
	 * Creates the english language file strings.
	 */
	public void languageFileEN() {
		if (!this.plugin.en.exists()) {
			this.plugin.cfgen.addDefault("EN.TAG", "&c[&ePlayerSupport&c] ");
			this.plugin.cfgen.addDefault("EN.NOCMDACCESS", "&cYou do not have access to this command!");
			this.plugin.cfgen.addDefault("EN.NOACTION", "&cYou are not allowed to do this!");
			this.plugin.cfgen.addDefault("EN.AUTHOR", "&6Developed by:");
			this.plugin.cfgen.addDefault("EN.CMD", "&6Commands:");
			this.plugin.cfgen.addDefault("EN.CMDHELP", "&cUse &6/sup help &cto get a list of commands.");
			this.plugin.cfgen.addDefault("EN.TOOMANYARGS", "&cToo many arguments!");
			this.plugin.cfgen.addDefault("EN.UNKNOWNCMD", "&cUnknown command!");
			this.plugin.cfgen.addDefault("EN.UNKNOWNCMDHELP", "&aType &6/sup help &afor a list of commands.");
			this.plugin.cfgen.addDefault("EN.CONFIGRELOAD", "&aSuccessfully reloaded config.yml.");
			this.plugin.cfgen.addDefault("EN.INFO", "&cShows info about PlayerSupport.");
			this.plugin.cfgen.addDefault("EN.CONFIGRELOADHELP", "&cReloads the config.yml.");
			this.plugin.cfgen.addDefault("EN.CMDLIST", "&cShows a list of commands.");
			this.plugin.cfgen.addDefault("EN.UPDATEMSG", "&aAn update is available download it here");
			this.plugin.cfgen.addDefault("EN.PLAYERNOTONLINE", "&cPlayer not online or not found!");
			this.plugin.cfgen.addDefault("EN.PLAYERNOTONLINENOHELP", "&cPlayer not online or does not need help!");
			this.plugin.cfgen.addDefault("EN.PLAYERNOTINLIST", "&cPlayer is not in the list!");
			this.plugin.cfgen.addDefault("EN.PLAYERREMOVE", "&aSuccessfully removed &6%s&a!");
			this.plugin.cfgen.addDefault("EN.NOTPLAYER", "&cYou are not a player!");
			this.plugin.cfgen.addDefault("EN.DENYMSG", "&cYou have been blocked to use this!");
			this.plugin.cfgen.addDefault("EN.REQUESTREVOKE", "&aSupport request revoked!");
			this.plugin.cfgen.addDefault("EN.SUPSEARCH", "&aSupporters are notified now...");
			this.plugin.cfgen.addDefault("EN.SUPHELP", "&aPlayer &e<&6%s&e> &aneeds help!");
			this.plugin.cfgen.addDefault("EN.NOSUPONLINE", "&cUnfortunately it is no supporter online!");
			this.plugin.cfgen.addDefault("EN.SUPCOUNTMSG", "&6%s &asupporters got your request!");
			this.plugin.cfgen.addDefault("EN.ALREADYADDED", "&cAlready added to the list!");
			this.plugin.cfgen.addDefault("EN.ADDLIST", "&aSuccessfully added &6%s &ato the list!");
			this.plugin.cfgen.addDefault("EN.PLAYERLIST", "&aPlayer needing help:");
			this.plugin.cfgen.addDefault("EN.HELPEDPLAYERINLIST", "&7- &6%s &c(is currently helped by &6%s&c)");
			this.plugin.cfgen.addDefault("EN.HELPSUP", "&cSends or revokes a supporter request.");
			this.plugin.cfgen.addDefault("EN.HELPLIST", "&cLists all players needing help.");
			this.plugin.cfgen.addDefault("EN.HELPACCEPT", "&cAccepts a support request.");
			this.plugin.cfgen.addDefault("EN.HELPSUPADD", "&cAdds a player to the list of supporters.");
			this.plugin.cfgen.addDefault("EN.HELPSUPREMOVE", "&cRemoves a player from the list of supporters.");
			this.plugin.cfgen.addDefault("EN.HELPALLOW", "&cAllows a player to use /sup (if denied).");
			this.plugin.cfgen.addDefault("EN.HELPDENY", "&cDisallows a player to use /sup.");
			this.plugin.cfgen.addDefault("EN.JOINHELPMSG", "&6%s &aplayer is/are requesting help!");
			this.plugin.cfgen.addDefault("EN.PLAYERTOSUPMSG", "&6[&ePlayer&6] &e%s&7: %s");
			this.plugin.cfgen.addDefault("EN.SUPTOPLAYERMSG", "&2[&aSup&2] &a%s&7: %s");
			this.plugin.cfgen.addDefault("EN.ALREADYHELPED", "&cPlayer gets help already!");
			this.plugin.cfgen.addDefault("EN.HELPMSG", "&aYou are helping now &6%s&a!");
			this.plugin.cfgen.addDefault("EN.HELPCHATMSG", "&aALL messages are now sent to him!");
			this.plugin.cfgen.addDefault("EN.GETHELPMSG", "&aYou are getting help now from &6%s&a!");
			this.plugin.cfgen.addDefault("EN.EXITCHAT", "&6%s &aexited the support chat!");
			this.plugin.cfgen.addDefault("EN.EXITCHATINFO", "&aEnter &6EXIT &ato exit the chat!");
			this.plugin.cfgen.addDefault("EN.EXITCHATSELF", "&aYou exited the support chat!");
			this.plugin.cfgen.addDefault("EN.ALREADYHELPING", "&cYou are helping somebody already!");
			this.plugin.cfgen.options().copyDefaults(true);
			try {
				this.plugin.cfgen.save(this.plugin.en);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates the german language file strings.
	 */
	public void languageFileDE() {
		if (!this.plugin.de.exists()) {
			this.plugin.cfgde.addDefault("DE.TAG", "&c[&ePlayerSupport&c] ");
			this.plugin.cfgde.addDefault("DE.NOCMDACCESS", "&cDu hast keinen Zugriff auf diesen Befehl!");
			this.plugin.cfgde.addDefault("DE.NOACTION", "&cDu hast keine Rechte dies zu tun!");
			this.plugin.cfgde.addDefault("DE.AUTHOR", "&6Entwickelt von:");
			this.plugin.cfgde.addDefault("DE.CMD", "&6Befehle:");
			this.plugin.cfgde.addDefault("DE.CMDHELP", "&cBenutze &6/sup help &cfuer eine Liste von Befehlen");
			this.plugin.cfgde.addDefault("DE.TOOMANYARGS", "&cZu viele Argumente!");
			this.plugin.cfgde.addDefault("DE.UNKNOWNCMD", "&cUnbekannter Befehl!");
			this.plugin.cfgde.addDefault("DE.UNKNOWNCMDHELP", "&aGebe &6/sup help &afuer eine Liste von Befehlen ein.");
			this.plugin.cfgde.addDefault("DE.CONFIGRELOAD", "&aDie config.yml wurde erfolgreich neugeladen.");
			this.plugin.cfgde.addDefault("DE.INFO", "&cZeigt Infos ueber PlayerSupport.");
			this.plugin.cfgde.addDefault("DE.CONFIGRELOADHELP", "&cLaedt die config.yml neu.");
			this.plugin.cfgde.addDefault("DE.CMDLIST", "&cZeigt eine Liste von Befehlen.");
			this.plugin.cfgde.addDefault("DE.UPDATEMSG", "&aEin Update ist verfuegbar, downloade es hier");
			this.plugin.cfgde.addDefault("DE.PLAYERNOTONLINE", "&cSpieler nicht online oder nicht gefunden!");
			this.plugin.cfgde.addDefault("DE.PLAYERNOTONLINENOHELP", "&cSpieler nicht online oder braucht keine Hilfe!");
			this.plugin.cfgde.addDefault("DE.PLAYERNOTINLIST", "&cSpieler ist nicht in der Liste!");
			this.plugin.cfgde.addDefault("DE.PLAYERREMOVE", "&aSpieler &6%s &aerfolgreich entfernt!");
			this.plugin.cfgde.addDefault("DE.NOTPLAYER", "&cDu bist kein Spieler!");
			this.plugin.cfgde.addDefault("DE.DENYMSG", "&cDu wurdest hierfuer gesperrt!");
			this.plugin.cfgde.addDefault("DE.REQUESTREVOKE", "&aSupport Anfrage zurueckgezogen!");
			this.plugin.cfgde.addDefault("DE.SUPSEARCH", "&aDie Supporter werden jetzt benachrichtigt...");
			this.plugin.cfgde.addDefault("DE.SUPHELP", "&aDer Spieler &e<&6%s&e> &abraucht Hilfe!");
			this.plugin.cfgde.addDefault("DE.NOSUPONLINE", "&cEs ist leider kein Supporter online!");
			this.plugin.cfgde.addDefault("DE.SUPCOUNTMSG", "&6%s &aSupporter haben deine Anfrage erhalten!");
			this.plugin.cfgde.addDefault("DE.ALREADYADDED", "&cBereits der Liste hinzugefuegt!");
			this.plugin.cfgde.addDefault("DE.ADDLIST", "&aSpieler &6%s &aerfolgreich zur Liste hinzugefuegt!");
			this.plugin.cfgde.addDefault("DE.PLAYERLIST", "&aSpieler, die Hilfe benoetigen:");
			this.plugin.cfgde.addDefault("DE.HELPEDPLAYERINLIST", "&7- &4%s &7(wird gerade geholfen von &6%s&7)");
			this.plugin.cfgde.addDefault("DE.HELPSUP", "&cSendet oder widerruft eine Support Anfrage.");
			this.plugin.cfgde.addDefault("DE.HELPLIST", "&cListet alle Hilfe brauchende Spieler auf.");
			this.plugin.cfgde.addDefault("DE.HELPACCEPT", "&cNimmt die Support Anfrage des Spielers an.");
			this.plugin.cfgde.addDefault("DE.HELPSUPADD", "&cFuegt einen Spieler der Supporter Liste hinzu.");
			this.plugin.cfgde.addDefault("DE.HELPSUPREMOVE", "&cEntfernt einen Spieler von der Supporter Liste.");
			this.plugin.cfgde.addDefault("DE.HELPALLOW", "&cErlaubt einem Spieler /sup (falls gesperrt).");
			this.plugin.cfgde.addDefault("DE.HELPDENY", "&cVerbietet einem Spieler /sup.");
			this.plugin.cfgde.addDefault("DE.JOINHELPMSG", "&6%s &aSpieler braucht/brauchen Hilfe!");
			this.plugin.cfgde.addDefault("DE.PLAYERTOSUPMSG", "&6[&eSpieler&6] &e%s&7: %s");
			this.plugin.cfgde.addDefault("DE.SUPTOPLAYERMSG", "&2[&aSup&2] &a%s&7: %s");
			this.plugin.cfgde.addDefault("DE.ALREADYHELPED", "&cDer Spieler bekommt bereits Hilfe!");
			this.plugin.cfgde.addDefault("DE.HELPMSG", "&aDu hilfst nun &6%s&a!");
			this.plugin.cfgde.addDefault("DE.HELPCHATMSG", "&aALLE Nachrichten werden nun an ihn gesendet!");
			this.plugin.cfgde.addDefault("DE.GETHELPMSG", "&aDir wird nun von &6%s &ageholfen!");
			this.plugin.cfgde.addDefault("DE.EXITCHAT", "&6%s &abeendete den Support Chat!");
			this.plugin.cfgde.addDefault("DE.EXITCHATINFO", "&aGebe &6EXIT &aein zum Verlassen des Chats!");
			this.plugin.cfgde.addDefault("DE.EXITCHATSELF", "&aDu hast den Support Chat verlassen!");
			this.plugin.cfgde.addDefault("DE.ALREADYHELPING", "&cDu hilfst bereits einem Spieler!");
			this.plugin.cfgde.options().copyDefaults(true);
			try {
				this.plugin.cfgde.save(this.plugin.de);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns language message.
	 * @param key Language key.
	 * @return Message.
	 */
	public String getLang(String key) {
		if (lg.get(key) == null) {
			return "&4Error: Missing language String!";
		} else {
			return lg.get(key);
		}
	}
}