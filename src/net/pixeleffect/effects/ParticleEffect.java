package net.pixeleffect.effects;


import java.util.Arrays;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.pixeleffect.PixelEffectPlugin;
import net.pixeleffect.effects.shapes.EffectShape;
import net.pixeleffect.utils.Particles;
import net.pixeleffect.utils.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;


public class ParticleEffect extends BukkitRunnable
{
	/**
	 * La forme que prendra l'effet.
	 */
	public EffectShape shape;

	/**
	 * Les différentes particules affichées
	 */
	public EnumParticle[] particleTypes;

	protected long updateCounter;

	protected long tickRate = 2;

	public BukkitTask bukkitTask;

	protected transient Player thePlayer;
	protected transient int currentParticleIndex;
	protected transient long delayBeforeStop;

	public ParticleEffect(EffectShape shape, EnumParticle... types)
	{
		this.shape = shape;
		this.particleTypes = types;
	}

	/**
	 * Lance l'effet autour du joueur donné pour le temps donné.
	 * 
	 * @param player
	 *            Le joueur autour duquel l'effet doit apparaître.
	 * @param time
	 *            Le temps au bout duquel l'effet doit s'arrêter.
	 * @return La tâche bukkit.
	 */
	protected BukkitTask startEffect(final Player player, final long time)
	{
		final BukkitTask task = runTaskTimer(PixelEffectPlugin.instance, 0, tickRate);
		thePlayer = player;
		
		delayBeforeStop = time;
		
		bukkitTask = task;

		return bukkitTask;
	}

	final double radialsPerStep = Math.PI / 4;
	
	@Override
	public void run()
	{
		updateCounter++;

		EnumParticle particle = nextParticle();

		Location loc = thePlayer.getLocation();
		loc.add(0, 0.5f, 0);
		loc.add(Math.cos(radialsPerStep * updateCounter) * 1.0f, 0, Math.sin(radialsPerStep * updateCounter) * 1.0F);

		Vector pos = shape.getLocation(updateCounter, thePlayer.getLocation());
		Particles.playSingleParticle(particle, Utils.locationFromVector(thePlayer.getWorld(), pos));
//		
		if (delayBeforeStop != -1 && updateCounter * tickRate > delayBeforeStop)
		{
			cancel();
		}
	}

	private EnumParticle nextParticle()
	{
		currentParticleIndex = (currentParticleIndex + 1) % particleTypes.length;

		return particleTypes[currentParticleIndex];
	}
	
	public String toString()
	{
		return "Effect{" + Arrays.toString(particleTypes) + "}";
	}
}
