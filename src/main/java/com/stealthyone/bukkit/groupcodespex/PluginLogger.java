package com.stealthyone.bukkit.groupcodespex;

import java.util.logging.Logger;


public final class PluginLogger {
	
	private BasePlugin plugin;
	
	private static Logger log = BasePlugin.getInstance().getServer().getLogger();
	
	public PluginLogger(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	public final void debug(String s) {
		if (plugin.isDebug()) {
			log.info("[" + BasePlugin.prefix + " DEBUG] " + s);
		}
	}
	
	public final void info(String s) {
		log.info("[" + BasePlugin.prefix + "] " + s);
	}
	
	public final void warning(String s) {
		log.warning("[" + BasePlugin.prefix + "] " + s);
	}
	
	public final void severe(String s) {
		log.severe("[" + BasePlugin.prefix + "] " + s);
	}
}