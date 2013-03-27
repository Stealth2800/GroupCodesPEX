package com.stealthyone.bukkit.groupcodespex.commands.subcommands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.utils.CodeManager;

public final class CmdGroupCodesList implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdGroupCodesList(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("groupcodes.list")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		CodeManager codeManager = plugin.getCodeManager();
		List<String> codeList = codeManager.getCodeList();
		
		sender.sendMessage(ChatColor.GOLD + "----Codes----");
		if (codeList.size() == 0) {
			sender.sendMessage(ChatColor.RED + "No codes!");
			return;
		}
		
		for (int i = 0; i < codeList.size(); i++) {
			String codeToAdd = codeList.get(i);
			String fullMessage = ChatColor.AQUA + Integer.toString(i) + ") " + ChatColor.GREEN + codeToAdd;
			plugin.getLog().debug("codeToAdd: " + codeToAdd);
			if (codeManager.isCodeClaimed(codeToAdd)) {
				plugin.getLog().debug("List creation: code is claimed: " + codeToAdd);
				fullMessage += ChatColor.RED + " (claimed: " + codeManager.getCodeClaimer(codeToAdd) + ")";
			}
			sender.sendMessage(fullMessage);
		}
	}

}