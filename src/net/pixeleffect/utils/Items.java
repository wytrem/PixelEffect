package net.pixeleffect.utils;


import java.util.Arrays;

import net.minecraft.server.v1_8_R1.ItemStack;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagList;

import org.bukkit.inventory.meta.ItemMeta;

public class Items
{
	public static boolean addGlow(org.bukkit.inventory.ItemStack stack)
	{
		ItemStack nmsStack = null;
		try
		{
			nmsStack = (ItemStack) Reflection.getValue(stack, "handle");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		NBTTagCompound compound = null;
		try
		{
			compound = (NBTTagCompound) Reflection.getValue(nmsStack, "tag");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

		if (compound == null)
		{
			compound = new NBTTagCompound();
			Reflection.setValue(nmsStack, "tag", compound);
		}

		// Empty enchanting compound
		compound.set("ench", new NBTTagList());
		
		return true;
	}
	

	public static org.bukkit.inventory.ItemStack setItemNameAndLore(org.bukkit.inventory.ItemStack item, String name, String... lore)
	{
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		return item;
	}
}
