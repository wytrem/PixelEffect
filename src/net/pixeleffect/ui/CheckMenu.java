package net.pixeleffect.ui;

import java.util.HashSet;

import net.pixeleffect.utils.EnchantGlow;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class CheckMenu extends Menu
{
	private HashSet<Integer> checkedSlots = new HashSet<Integer>();
	private int closeSlot;
	
	public CheckMenu(InventoryHolder holder, int size, String title)
	{
		super(holder, size, title);
		setShouldCloseAfterClick(false);
	}
	
	public void setCloseSlot(int slot)
	{
		closeSlot = slot;
	}
	
	public void onItemClicked(int slot, Player player, InventoryClickEvent event)
	{
	    ItemStack stack = getInventory().getItem(slot);
	    
	    if (stack == null)
	    {
	    	return;
	    }
	    
	    if (slot == closeSlot)
	    {
	    	validate(player);
	    	close(player);
	    	return;
	    }
	    
	    EnchantGlow.addGlow(stack);
	    checkedSlots.add(Integer.valueOf(slot));
	}
	
	public HashSet<ItemStack> getCheckedStacks()
	{
		HashSet<ItemStack> list = new HashSet<ItemStack>();
		
		for (Integer integer : checkedSlots)
		{
			list.add(getInventory().getItem(integer.intValue()));
		}
		
		return list;
	}

	public void validate(Player player) {}
}
