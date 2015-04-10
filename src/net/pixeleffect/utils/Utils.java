package net.pixeleffect.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class Utils
{
	public static Location locationFromVector(World world, Vector vec)
	{
		return new Location(world, vec.getX(), vec.getY(), vec.getZ());
	}
	
	public static float getSign(float f)
	{
		if (f < 0)
		{
			return -1.0f;
		}
		else if (f > 0)
		{
			return 1.0f;
		}
		else
		{
			return 0.0f;
		}
	}
}
