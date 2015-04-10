package net.pixeleffect.effects.shapes;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ShapeCircle extends EffectShape
{
	public ShapeCircle()
	{
		
	}
	
	public Vector getLocation(long time, Location location)
	{
		double angle = Math.PI / 12 * time;
		float radius = 0.7f;
		
		Vector vector = new Vector(location.getX() + Math.cos(angle) * radius, location.getY() + 2.0f, location.getZ() + Math.sin(angle) * radius);
		return vector;
	}
}
