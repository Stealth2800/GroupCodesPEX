package com.stealthyone.bukkit.groupcodespex.commands.subcommands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;
import com.stealthyone.bukkit.groupcodespex.messages.GenericMessage;
import com.stealthyone.bukkit.groupcodespex.messages.UsageMessage;
import com.stealthyone.bukkit.groupcodespex.utils.CodeManager;

public final class CmdGroupCodesAdd implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdGroupCodesAdd(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("groupcodes.add")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		CodeManager codeManager = plugin.getCodeManager();
		
		if (args.length < 3) {
			UsageMessage.GROUPCODES_ADD.sendTo(sender);
			return;
		}
		
		String group = args[1];
		String code = args[2];
		if (code.equalsIgnoreCase("random")) {
			code = codeManager.getRandomCode();
		}
		
		/* Make sure group exists */
		if (!plugin.getPex().isGroupExists(group)) {
			GenericMessage.GROUP_DOESNT_EXIST.sendTo(sender, Arrays.asList("{GROUP}|" + group));
			return;
		}
		
		/* Make sure code doesn't already exist */
		if (codeManager.isCodeExists(code)) {
			GenericMessage.CODE_ALREADY_EXISTS.sendTo(sender, Arrays.asList("{CODE}|" + code));
			return;
		}
		
		/* Every precheck is fine, go ahead and create code */
		codeManager.createCode(group, code);
		plugin.getMethods().sendTaggedMessage(sender, "You successfully created code: " + code + " for group: " + group);
	}

}
