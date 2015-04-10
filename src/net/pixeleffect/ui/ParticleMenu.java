package net.pixeleffect.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.server.v1_8_R1.EnumChatFormat;
import net.minecraft.server.v1_8_R1.EnumParticle;
import net.pixeleffect.effects.Manager;
import net.pixeleffect.effects.ParticleEffect;
import net.pixeleffect.effects.shapes.EffectShape;
import net.pixeleffect.utils.Items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class ParticleMenu
{
	public static final HashMap<Material, EnumParticle> itemMaterialToParticleMapping = new HashMap<>();

	static
	{
		// TODO : faire en sorte que ça soit configurable
		itemMaterialToParticleMapping.put(Material.NETHER_STAR, EnumParticle.FIREWORKS_SPARK);
		itemMaterialToParticleMapping.put(Material.PRISMARINE_CRYSTALS, EnumParticle.WATER_BUBBLE);
		itemMaterialToParticleMapping.put(Material.WEB, EnumParticle.CLOUD);
		itemMaterialToParticleMapping.put(Material.SNOW_BALL, EnumParticle.SNOWBALL);
		itemMaterialToParticleMapping.put(Material.SLIME_BALL, EnumParticle.SLIME);
		itemMaterialToParticleMapping.put(Material.FIREBALL, EnumParticle.FLAME);
		itemMaterialToParticleMapping.put(Material.WATER_BUCKET, EnumParticle.WATER_DROP);
		itemMaterialToParticleMapping.put(Material.WOOD_AXE, EnumParticle.LAVA);
		itemMaterialToParticleMapping.put(Material.ENCHANTMENT_TABLE, EnumParticle.ENCHANTMENT_TABLE);
		itemMaterialToParticleMapping.put(Material.BEDROCK, EnumParticle.TOWN_AURA);
		itemMaterialToParticleMapping.put(Material.POTION, EnumParticle.SPELL);
		itemMaterialToParticleMapping.put(Material.NOTE_BLOCK, EnumParticle.NOTE);
		itemMaterialToParticleMapping.put(Material.REDSTONE, EnumParticle.HEART);
	}

	public static void addItemsToInventory(Menu menu)
	{
		Iterator<Entry<Material, EnumParticle>> iterator = itemMaterialToParticleMapping.entrySet().iterator();

		int index = 0;

		while (iterator.hasNext())
		{
			Entry<Material, EnumParticle> entry = iterator.next();
			menu.setItem(index, Items.setItemNameAndLore(new ItemStack(entry.getKey()), entry.getValue().name()));
			index++;
		}
	}

	public static Menu getParticleMenu(final Player player)
	{

		final CheckMenu menu = new CheckMenu(player, 27, "Choose your particles") {
			public void validate(Player player)
			{
				Manager.startEffect(player, new ParticleEffect(EffectShape.circle, stacksToParticles(getCheckedStacks())), -1);
			}
		};

		addItemsToInventory(menu);
		
		menu.setItem(26, Items.setItemNameAndLore(new ItemStack(Material.EMERALD_BLOCK), EnumChatFormat.GREEN + "Validate"));
		menu.setCloseSlot(26);
		
		return menu;
	}
	
	public static EnumParticle[] stacksToParticles(Collection<ItemStack> stacks)
	{
		ArrayList<EnumParticle> list = new ArrayList<EnumParticle>();
		
		Iterator<ItemStack> iterator = stacks.iterator();
		
		while (iterator.hasNext())
		{
			ItemStack stack = iterator.next();
			
			list.add(itemMaterialToParticleMapping.get(stack.getType()));
		}
		
		return list.toArray(new EnumParticle[list.size()]);
	}
}
