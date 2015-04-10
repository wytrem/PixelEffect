package net.pixeleffect.effects.shapes;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class EffectShape
{
	public abstract Vector getLocation(long time, Location location);
	
	public static final EffectShape circle = new ShapeCircle();
}
