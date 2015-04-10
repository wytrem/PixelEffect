package net.pixeleffect.effects;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;


public class Manager
{
	private static final HashMap<UUID, ArrayList<BukkitTask>> runningEffects = new HashMap<UUID, ArrayList<BukkitTask>>();
	
	public static void startEffect(Player player, ParticleEffect effect, long delay)
	{
		UUID uuid = player.getUniqueId();

		
		if (!runningEffects.containsKey(uuid))
		{
			add(player);
		}
		ArrayList<BukkitTask> tasks = runningEffects.get(uuid);
		tasks.add(effect.startEffect(player, delay));
	}

	public static void stopAllEffects(UUID uuid)
	{
		ArrayList<BukkitTask> tasks = runningEffects.get(uuid);
		
		for (BukkitTask task : tasks)
		{
			task.cancel();
		}
		
		tasks.clear();
	}
	
	public static void stopAllEffects(Player player)
	{
		stopAllEffects(player.getUniqueId());
	}
	
	public static void add(Player player)
	{
		runningEffects.put(player.getUniqueId(), new ArrayList<BukkitTask>());
	}
	
	public static void remove(Player player)
	{
		UUID uuid = player.getUniqueId();
		
		if (runningEffects.containsKey(uuid))
		{
			stopAllEffects(uuid);
			runningEffects.remove(uuid);
		}
	}
	
	public static void clear()
	{
		for (UUID id : runningEffects.keySet())
		{
			stopAllEffects(id);
		}
		
		runningEffects.clear();
	}
}
