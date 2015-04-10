package net.pixeleffect.utils;


import java.util.HashMap;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class Particles
{
	private static final int SEND_DISTANCE = 16;

	public static void playParticles(EnumParticle particle, Location location)
	{
		playParticles(location.getWorld(), particle, location, 0.0F, 0.0F, 0.0F, 0.0F, 1);
	}

	public static void playParticles(World world, EnumParticle particle, Location location, float speed, int count)
	{
		playParticles(world, particle, location, 0.0F, 0.0F, 0.0F, speed, count);
	}

	public static void playParticles(World world, EnumParticle particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count)
	{
		for (Player player : world.getEntitiesByClass(Player.class))
		{
			if (location.distance(player.getLocation()) <= SEND_DISTANCE)
			{
				sendParticlesToPlayer(particle, player, location, offsetX, offsetY, offsetZ, speed, count);
			}
		}
	}

	private static void sendParticlesToPlayer(EnumParticle particle, Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count)
	{
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
		Reflection.setValue(packet, "a", particle);
		Reflection.setValue(packet, "b", (float) location.getX());
		Reflection.setValue(packet, "c", (float) location.getY());
		Reflection.setValue(packet, "d", (float) location.getZ());
		Reflection.setValue(packet, "e", offsetX);
		Reflection.setValue(packet, "f", offsetY);
		Reflection.setValue(packet, "g", offsetZ);
		Reflection.setValue(packet, "h", speed);
		Reflection.setValue(packet, "i", count);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void playSingleParticle(EnumParticle particle, Location location)
	{
		playSingleParticle(location.getWorld(), particle, location, 0.0F, 0.0F, 0.0F, 0.0F);
	}

	public static void playSingleParticle(World world, EnumParticle particle, Location location)
	{
		playSingleParticle(world, particle, location, 0.0F, 0.0F, 0.0F, 0.0F);
	}

	public static void playSingleParticle(World world, EnumParticle particle, Location location, float motionX, float motionY, float motionZ, float speed)
	{
		for (Player player : world.getEntitiesByClass(Player.class))
		{
			if (location.distance(player.getLocation()) <= SEND_DISTANCE)
			{
				sendSingleParticleToPlayer(particle, player, location, motionX, motionY, motionZ, speed);
			}
		}
	}

	private static void sendSingleParticleToPlayer(EnumParticle particle, Player player, Location location, float motionX, float motionY, float motionZ, float speed)
	{
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
		Reflection.setValue(packet, "a", particle);
		Reflection.setValue(packet, "b", (float) location.getX());
		Reflection.setValue(packet, "c", (float) location.getY());
		Reflection.setValue(packet, "d", (float) location.getZ());
		Reflection.setValue(packet, "e", motionX);
		Reflection.setValue(packet, "f", motionY);
		Reflection.setValue(packet, "g", motionZ);
		Reflection.setValue(packet, "h", speed);
		Reflection.setValue(packet, "i", 0);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static final HashMap<String, EnumParticle> nameToParticleMapping = new HashMap<String, EnumParticle>();
	
	static
	{
		EnumParticle[] values = EnumParticle.values();
		
		for (int i = 0; i < values.length; i++)
		{
			EnumParticle particle = values[i];
			
			nameToParticleMapping.put(particle.name(), particle);
		}
	}

	public static EnumParticle fromName(String name)
	{
		EnumParticle value = nameToParticleMapping.get(name.toUpperCase());
		return value != null ? value : EnumParticle.FLAME;
	}
}
