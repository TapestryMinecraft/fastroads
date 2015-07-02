package com.github.itsnickbarry.fastroads;

import org.bukkit.plugin.java.JavaPlugin;

public class FastRoads extends JavaPlugin {

	@Override
	public void onEnable(){
		this.saveDefaultConfig();
		FastRoadsListener.loadConstants();
		getServer().getPluginManager().registerEvents(new FastRoadsListener(), this);
	}
	
	@Override
	public void onDisable(){
		
	}
}
