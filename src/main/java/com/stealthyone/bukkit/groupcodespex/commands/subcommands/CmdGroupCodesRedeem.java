package com.stealthyone.bukkit.groupcodespex.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.messages.UsageMessage;
import com.stealthyone.bukkit.groupcodespex.utils.CodeManager;

public final class CmdGroupCodesRedeem implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdGroupCodesRedeem(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("groupcodes.redeem")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		if (args.length < 2) {
			UsageMessage.GROUPCODES_REDEEM.sendTo(sender);
			return;
		}
		
		CodeManager codeManager = plugin.getCodeManager();
		
		String code = args[1];
		
		if (!codeManager.isCodeExists(code)) {
			plugin.getMethods().sendTaggedMessage(sender, ChatColor.DARK_RED + code + ChatColor.RED + " does not exist!");
			return;
		}
		
		if (codeManager.isCodeClaimed(code)) {
			plugin.getMethods().sendTaggedMessage(sender, ChatColor.DARK_RED + code + ChatColor.RED + " is already claimed!");
			return;
		}
		
		codeManager.claimCode(sender, code);
		return;
	}

}
