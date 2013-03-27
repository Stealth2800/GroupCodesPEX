package com.stealthyone.bukkit.groupcodespex;

import org.bukkit.plugin.java.JavaPlugin;

import com.stealthyone.bukkit.groupcodespex.commands.CmdGroupCodes;
import com.stealthyone.bukkit.groupcodespex.permissions.PermissionManagerPEX;
import com.stealthyone.bukkit.groupcodespex.utils.CodeManager;

public final class BasePlugin extends JavaPlugin {

	public static final String prefix = "GroupCodes";
	private static BasePlugin instance;
	{
		instance = this;
	}
	
	private PluginLogger log;
	private PluginConfig config;
	private PluginMethods methods;
	private CodeManager codeManager;
	
	private PermissionManagerPEX pex;
	
	@Override
	public void onEnable() {
		/* Setup config */
		config = new PluginConfig(this);
		config.load();
		
		/* Setup log */
		log = new PluginLogger(this);
		
		/* Setup methods */
		methods = new PluginMethods(this);
		
		/* Setup permissions */
		pex = new PermissionManagerPEX(this);
		
		/* Setup misc important stuff */
		codeManager = new CodeManager(this);
		
		/* Setup commands */
		getCommand("groupcodes").setExecutor(new CmdGroupCodes(this));
		
		log.debug("v" + this.getVersion() + " by Stealth2800 enabled!");
	}
	
	@Override
	public void onDisable() {
		log.debug("v" + this.getVersion() + " by Stealth2800 disabled!");
	}
	
	public final static BasePlugin getInstance() {
		return instance;
	}
	
	public final boolean isDebug() {
		return this.getConfig().getBoolean("Debug");
	}
	
	public final String getVersion() {
		return this.getDescription().getVersion();
	}
	
	public final PluginLogger getLog() {
		return this.log;
	}
	
	public final PermissionManagerPEX getPex() {
		return this.pex;
	}
	
	public final PluginMethods getMethods() {
		return this.methods;
	}
	
	public final CodeManager getCodeManager() {
		return this.codeManager;
	}
}