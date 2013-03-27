package com.stealthyone.bukkit.groupcodespex.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;

public final class CmdGroupCodesReload implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdGroupCodesReload(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("groupcodes.reload")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		plugin.reloadConfig();
		plugin.getCodeManager().reloadCodeConfig();
		plugin.getMethods().sendTaggedMessage(sender, ChatColor.RED + "v" + plugin.getVersion() + " reloaded");
	}

}
