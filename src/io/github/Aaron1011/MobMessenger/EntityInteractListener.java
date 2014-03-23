package io.github.Aaron1011.MobMessenger;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityInteractListener implements Listener {
	private JavaPlugin plugin;
	private HashMap<Inventory, Entity> inventories = new HashMap<Inventory, Entity>();
	private HashMap<Entity, Inventory> entites = new HashMap<Entity, Inventory>();
	private HashMap<Entity, ItemStack[]> items = new HashMap<Entity, ItemStack[]>();
	

	public EntityInteractListener(MobMessenger plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		Inventory inventory;
		Bukkit.broadcastMessage("Hi!");
		if (!items.containsKey(event.getRightClicked())) {
			Bukkit.broadcastMessage("If!");
			inventory = Bukkit.createInventory(event.getPlayer(), 18);
			
			event.getPlayer().openInventory(inventory);
			//inventory.setItem(0, new ItemStack(Material.DIAMOND));
			inventories.put(inventory, event.getRightClicked());
			entites.put(event.getRightClicked(), inventory);
			items.put(event.getRightClicked(), inventory.getContents());
		}
		else {
			Bukkit.broadcastMessage("Else!");
			inventory = entites.get(event.getRightClicked());
			Bukkit.broadcastMessage("Length: " + Integer.toString(items.get(event.getRightClicked()).length));
			inventory.setContents(items.get(event.getRightClicked()));
			inventory.setItem(5, items.get(event.getRightClicked())[0]);
			//inventory.setItem(0, new ItemStack(Material.DIRT));
			event.getPlayer().openInventory(inventory);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		items.put(inventories.get(event.getInventory()), event.getInventory().getContents());
		Bukkit.broadcastMessage(Boolean.toString(event.getInventory().contains(new ItemStack(Material.GLASS))));
	}
}
