package io.github.Aaron1011.MobMessenger;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class MobMessenger extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.broadcastMessage("Hi!");
		getServer().getPluginManager().registerEvents(new EntityInteractListener(this), this);
	}
}
