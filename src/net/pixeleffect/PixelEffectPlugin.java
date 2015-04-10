package net.pixeleffect;


import java.io.File;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.pixeleffect.effects.Manager;
import net.pixeleffect.effects.ParticleEffect;
import net.pixeleffect.effects.shapes.EffectShape;
import net.pixeleffect.ui.ParticleMenu;
import net.pixeleffect.ui.UiManager;
import net.pixeleffect.utils.conf.Configuration;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class PixelEffectPlugin extends JavaPlugin
{
	public static PixelEffectPlugin instance;
	public Configuration config;

	@Override
	public void onEnable()
	{

		instance = this;
		config = new Configuration(new File(getDataFolder(), "config.yml"));

		try
		{
			config.load();
			getLogger().info("Successfully loaded configuration from '" + config.getFile().getAbsolutePath() + "'.");
		}
		catch (InvalidConfigurationException e)
		{
			e.printStackTrace();
			getLogger().warning("Couldn't load configuration from '" + config.getFile().getAbsolutePath() + "'. Use default config.");
		}
		
		getServer().getPluginManager().registerEvents(new UiManager(), this);
	}

	@Override
	public void onDisable()
	{

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("pixeleffect") || cmd.getName().equalsIgnoreCase("pe"))
		{
			if (args.length == 0)
			{
				if (sender instanceof Player)
				{
					Player player = (Player) sender;
					ParticleMenu.getParticleMenu(player).open(player);
				}
				else
				{
					printHelp(sender);
				}
			}
			else if (args.length == 1)
			{
				if (args[0].equals("help"))
				{
					printHelp(sender);
				}
				else if (args[0].equals("debug"))
				{
					if (sender instanceof Player)
					{
						Player player = (Player) sender;
						sender.sendMessage("Azertylol");

						Manager.startEffect(player, new ParticleEffect(EffectShape.circle, EnumParticle.FLAME), 60 * 20);
					}
				}
				else if (args[0].equals("stop"))
				{
					if (sender instanceof Player)
					{
						Player player = (Player) sender;
						Manager.stopAllEffects(player);
					}
				}
				
				else if (args[0].equals("clear"))
				{
					if (sender.isOp())
					{
						Manager.clear();
					}
				}
			}

			return true;
		}

		return false;
	}

	static void printHelp(CommandSender sender)
	{
		sender.sendMessage("--- PixelEffect help ---");
		sender.sendMessage("/pe");
	}
}
