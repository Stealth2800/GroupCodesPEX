package com.stealthyone.bukkit.groupcodespex.messages;

import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.PluginLogger;
import com.stealthyone.bukkit.groupcodespex.PluginMethods;

public enum GenericMessage {

	CODE_ALREADY_EXISTS(ChatColor.RED + "Code: " + ChatColor.DARK_RED + "{CODE}" + ChatColor.RED + " already exists!"),
	GROUP_DOESNT_EXIST(ChatColor.RED + "Group: " + ChatColor.DARK_RED + "{GROUP}" + ChatColor.RED + " doesn't exist!"),
	NO_PERMISSION(ChatColor.RED + "You do not have permission to use this command!");

	private String message;
	private boolean isTagged;

	private GenericMessage(String message) {
		this(message, true);
	}

	private GenericMessage(String message, boolean isTagged) {
		this.message = message;
	}

	public final void sendTo(CommandSender target) {
		if (this.isTagged) {
			BasePlugin.getInstance().getMethods().sendTaggedMessage(target, this.message);
		} else {
			target.sendMessage(this.message);
		}
	}
	
	public final void sendTo(CommandSender sender, List<String> replacements) {
		PluginLogger log = BasePlugin.getInstance().getLog();
		
		String newMessage = this.message;
		log.debug("this.message: " + newMessage);
		
		if (replacements != null) {
			log.debug("replacements not null for: " + message);
			for (int i = 0; i < replacements.size(); i++) {
				String[] tempString = replacements.get(i).split(Pattern.quote("|"));
				log.debug("replacing " + tempString[0] + " with " + tempString[1]);
				log.debug("newMessage pre: " + newMessage);
				newMessage = newMessage.replace(tempString[0], tempString[1]);
				log.debug("newMessage post: " + newMessage);
			}
		}
		
		PluginMethods methods = BasePlugin.getInstance().getMethods();
		
		if (isTagged) {
			methods.sendTaggedMessage(sender, ChatColor.translateAlternateColorCodes('&', newMessage));
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', newMessage));
		}
	}
}