package com.stealthyone.bukkit.groupcodespex;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class PluginMethods {

	private BasePlugin plugin;
	
	public PluginMethods(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	public final void sendTaggedMessage(CommandSender sender, String msg) {
		sender.sendMessage(ChatColor.GOLD + "[" + BasePlugin.prefix + "] " + ChatColor.GREEN + msg);
	}
}