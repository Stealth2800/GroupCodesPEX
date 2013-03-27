package com.stealthyone.bukkit.groupcodespex.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesAdd;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesList;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesRedeem;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesReload;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesRemove;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.ISubCmd;

public final class CmdGroupCodes implements CommandExecutor {

	private BasePlugin plugin;
	
	public CmdGroupCodes(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			showHelp(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("add")) {
				ISubCmd command = new CmdGroupCodesAdd(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("list")) {
				ISubCmd command = new CmdGroupCodesList(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("redeem")) {
				ISubCmd command = new CmdGroupCodesRedeem(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("reload")) {
				ISubCmd command = new CmdGroupCodesReload(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("remove")) {
				ISubCmd command = new CmdGroupCodesRemove(plugin);
				command.run(sender, args);
				return true;
			}
		}
		showHelp(sender);
		return true;
	}
	
	private final void showHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "----Codes----");
		sender.sendMessage(ChatColor.YELLOW + "/code redeem <code>" + ChatColor.GOLD + " - Redeems a code");
		sender.sendMessage(ChatColor.YELLOW + "/code list" + ChatColor.GOLD + " - Lists all codes");
		sender.sendMessage(ChatColor.YELLOW + "/code add <group> <code>" + ChatColor.GOLD + " - Adds a code");
		sender.sendMessage(ChatColor.YELLOW + "/code add <group> random" + ChatColor.GOLD + " - Adds a random code for a group");
		sender.sendMessage(ChatColor.YELLOW + "/code remove <code>" + ChatColor.GOLD + " - Removes a code");
	}
}