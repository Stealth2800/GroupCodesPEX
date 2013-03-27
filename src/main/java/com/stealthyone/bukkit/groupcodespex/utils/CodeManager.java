package com.stealthyone.bukkit.groupcodespex.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;

public final class CodeManager {

	private BasePlugin plugin;
	
	private CustomFileManager codeFile;
	
	public CodeManager(BasePlugin plugin) {
		this.plugin = plugin;
		
		codeFile = new CustomFileManager(plugin, "codes");
	}
	
	/**
	 * Returns the code config
	 * @return
	 */
	private final FileConfiguration getCodeConfig() {
		return codeFile.getConfig();
	}
	
	/**
	 * Returns a list of codes from the save file
	 * @return
	 */
	public final List<String> getCodeList() {
		List<String> returnList = new ArrayList<String>();
		
		for (String key : getCodeConfig().getConfigurationSection("codes").getValues(false).keySet()) {
			returnList.add(key);
		}
		
		plugin.getLog().debug("codelist: " + returnList.toString());
		
		return returnList;
	}
	
	/**
	 * Returns if a specified code exists or not
	 * @param code
	 * @return
	 */
	public final boolean isCodeExists(String code) {
		return getCodeConfig().getConfigurationSection("codes." + code) != null;
	}
	
	/**
	 * Claims a code for the sender
	 * @param sender
	 * @param code
	 */
	public final void claimCode(CommandSender sender, String code) {
		getCodeConfig().set("codes." + code + ".claimed", true);
		getCodeConfig().set("codes." + code + ".claimer", sender.getName());
		codeFile.saveFile();
		
		plugin.getMethods().sendTaggedMessage(sender, "You successfully claimed code: " + code + " and will now be promoted to a: " + getCodeGroup(code));
		plugin.getPex().setGroup(sender.getName(), Arrays.asList(getCodeGroup(code)));
		plugin.getMethods().sendTaggedMessage(sender, "You are now a(n): " + getCodeGroup(code));
	}
	
	/**
	 * Generates a random 8 digit long code
	 * @return
	 */
	public final String getRandomCode() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		
		return sb.toString();
	}
	
	/**
	 * Creates a code
	 * @param code
	 */
	public final void createCode(String group, String code) {
		getCodeConfig().set("codes." + code + ".group", group);
		getCodeConfig().set("codes." + code + ".claimed", false);
		getCodeConfig().set("codes." + code + ".claimer", "none");
		
		codeFile.saveFile();
	}
	
	/**
	 * Returns if a code is claimed or not
	 * @param code
	 * @return
	 */
	public final boolean isCodeClaimed(String code) {
		plugin.getLog().debug("(isCodeClaimed) code: " + code);
		boolean codeClaimed = !getCodeConfig().getString("codes." + code + ".claimer").equalsIgnoreCase("none");
		plugin.getLog().debug("code: " + code + " is claimed: " + codeClaimed);
		return codeClaimed;
	}
	
	/**
	 * Returns the claimer of a code
	 * @param code
	 * @return
	 */
	public final String getCodeClaimer(String code) {
		return getCodeConfig().getString("codes." + code + ".claimer");
	}
	
	/**
	 * Returns the group the code is associated with
	 * @param code
	 * @return
	 */
	public final String getCodeGroup(String code) {
		return getCodeConfig().getString("codes." + code + ".group");
	}
	
	/**
	 * Reloads the code file
	 * @return
	 */
	public final void reloadCodeConfig() {
		this.codeFile.reloadConfig();
	}
}