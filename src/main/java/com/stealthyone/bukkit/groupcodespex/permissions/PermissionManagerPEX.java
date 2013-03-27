package com.stealthyone.bukkit.groupcodespex.permissions;

import java.util.List;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.stealthyone.bukkit.groupcodespex.BasePlugin;

public final class PermissionManagerPEX {

	private BasePlugin plugin;
	private PermissionManager manager;

	public PermissionManagerPEX(BasePlugin plugin) {
		this.plugin = plugin;
		manager = PermissionsEx.getPermissionManager();
	}
	
	public final void setGroup(Player target, String[] groups) {
		setGroup(target.getName(), groups);
	}
	
	public final void setGroup(String targetName, List<String> groups) {
		setGroup(targetName, (String[]) groups.toArray());
	}
	
	public final void setGroup(String targetName, String[] groups) {
		PermissionUser user = manager.getUser(targetName);
		user.setGroups(groups);
	}
	
	public final boolean isGroupExists(String group) {
		return !manager.getGroup(group).isVirtual();
	}
	
}