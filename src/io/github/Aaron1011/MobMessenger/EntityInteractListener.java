package io.github.Aaron1011.MobMessenger;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityInteractListener implements Listener {
	private JavaPlugin plugin;
	private HashMap<Entity, Inventory> entities = new HashMap<Entity, Inventory>();
	private HashMap<HumanEntity, Entity> players = new HashMap<HumanEntity, Entity>();
	

	public EntityInteractListener(MobMessenger plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		Inventory inventory;
		players.put(event.getPlayer(), event.getRightClicked());
		if (!entities.containsKey(event.getRightClicked())) {
			inventory = Bukkit.createInventory(event.getPlayer(), 18);	
			event.getPlayer().openInventory(inventory);
			entities.put(event.getRightClicked(), inventory);
		}
		else {
			inventory = entities.get(event.getRightClicked());
			event.getPlayer().openInventory(inventory);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Entity entity = players.get(event.getPlayer());
		if (entity != null) {
			players.put(event.getPlayer(), null);
			entities.put(entity, event.getInventory());
		}
	}
}
