package io.github.Aaron1011.MobMessenger;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityInteractListener implements Listener {
	private JavaPlugin plugin;
	private HashMap<InventoryView, Entity> inventories = new HashMap<InventoryView, Entity>();
	private HashMap<Entity, InventoryView> entites = new HashMap<Entity, InventoryView>();
	private HashMap<Entity, ItemStack[]> items = new HashMap<Entity, ItemStack[]>();
	

	public EntityInteractListener(MobMessenger plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		InventoryView inventory;
		if (!items.containsKey(event.getRightClicked())) {
			inventory = event.getPlayer().openInventory(Bukkit.createInventory(event.getPlayer(), 18));
			inventory.setItem(0, new ItemStack(Material.DIAMOND));
			inventories.put(inventory, event.getRightClicked());
			entites.put(event.getRightClicked(), inventory);
			items.put(event.getRightClicked(), inventory.getTopInventory().getContents());
		}
		else {
			event.getPlayer().openInventory(entites.get(event.getRightClicked()));
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		items.put(inventories.get(event.getInventory()), event.getInventory().getContents());
	}
}
