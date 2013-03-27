package com.stealthyone.bukkit.groupcodespex.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesAdd;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesList;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesRedeem;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.CmdGroupCodesReload;
import com.stealthyone.bukkit.groupcodespex.commands.subcommands.ISubCmd;
import com.stealthyone.bukkit.groupcodespex.messages.UsageMessage;

public final class CmdGroupCodes implements CommandExecutor {

	private BasePlugin plugin;
	
	public CmdGroupCodes(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			UsageMessage.GROUPCODES.sendTo(sender);
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
			}
		}
		UsageMessage.GROUPCODES.sendTo(sender);
		return true;
	}
}