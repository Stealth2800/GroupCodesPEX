package com.stealthyone.bukkit.groupcodespex.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum UsageMessage {

	GROUPCODES("/groupcodes"),
	GROUPCODES_ADD("/groupcodes add <group> <code>"),
	GROUPCODES_REDEEM("/groupcodes redeem <code>");
	
	private String usageMessage;
	
	private UsageMessage(String msg) {
		this.usageMessage = msg;
	}
	
	public final String getFullMessage() {
		return this.usageMessage;
	}
	
	public final void sendTo(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_RED + "USAGE: " + ChatColor.RED + this.usageMessage);
	}
}
