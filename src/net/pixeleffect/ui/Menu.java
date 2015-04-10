package net.pixeleffect.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class Menu
{
	private Inventory inventory;
	private boolean shouldCloseAfterClick = true;
	
	public Menu(InventoryHolder holder, int size, String title)
	{
		inventory = Bukkit.createInventory(holder, size, title);
	}
	
	public void onItemClicked(int pos, Player player, InventoryClickEvent event) {}
	
	public void onClosed(Player player) {}

	public Inventory getInventory()
	{
		return inventory;
	}

	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
	}

	public boolean shouldCloseAfterClick()
	{
		return shouldCloseAfterClick;
	}

	public void setShouldCloseAfterClick(boolean shouldCloseAfterClick)
	{
		this.shouldCloseAfterClick = shouldCloseAfterClick;
	}

	public void setItem(int index, ItemStack itemStack)
	{
		inventory.setItem(index, itemStack);
	}
	
	public void open(Player player)
	{
		UiManager.open(this, player);
	}
	
	public void close(Player player)
	{
		UiManager.close(this, player);
	}
}
