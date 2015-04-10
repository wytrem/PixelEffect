package net.pixeleffect.utils.conf;

import java.io.File;
import java.util.Arrays;

public class Configuration extends Skyoconfig
{
	public Configuration(File configFile)
	{
		super(configFile, Arrays.asList("HypixelEffect Configuration"));
	}
	
	@ConfigOptions(name = "hypixelconfig.angle")
	public int angleparticule = 4; 
}
