package net.pixeleffect.ui;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UiManager implements Listener
{
	public static final HashMap<UUID, Menu> openMenus = new HashMap<UUID, Menu>();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		HumanEntity humanEntity = event.getWhoClicked();
		
		if (humanEntity instanceof Player)
		{
			Player player = (Player) humanEntity;
			UUID uuid = player.getUniqueId();
			
			if (openMenus.containsKey(uuid))
			{
				Menu menu = openMenus.get(uuid);
				
				if (player.getOpenInventory().getTopInventory().equals(menu.getInventory()))
				{
					event.setCancelled(true);
					
					menu.onItemClicked(event.getSlot(), player, event);
					
					if (menu.shouldCloseAfterClick())
					{
						close(menu, player);
					}
				}
			}
		}
	}
	
	public static void open(Menu menu, Player player)
	{
		openMenus.put(player.getUniqueId(), menu);
		player.openInventory(menu.getInventory());
	}
	
	public static void close(Menu menu, Player player)
	{
		menu.onClosed(player);
		player.closeInventory();
		openMenus.remove(player.getUniqueId());
	}
	
}
