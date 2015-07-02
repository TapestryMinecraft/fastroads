package com.github.itsnickbarry.fastroads;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FastRoadsListener implements Listener {
	
	private static double expFactor;
	private static HashMap<UUID, Double> expOwed = new HashMap<UUID, Double>();
	private static HashMap<Material, Float> speeds = new HashMap<Material, Float>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		/*
		 * Exp
		 */
		double distance = e.getFrom().distance(e.getTo());
		
		Player p = e.getPlayer();
		
		if (distance == 0 || p.isInsideVehicle()){
			return;
		}
		
		UUID id = p.getUniqueId();
		
		if (expOwed.get(id) == null){
			expOwed.put(id, distance * expFactor);
		} else {
			expOwed.put(id, expOwed.get(id) + (distance * expFactor));
		}
		
		if (expOwed.get(id) > 1){
			p.giveExp(1);
			expOwed.put(id, expOwed.get(id) - 1);
		}
		/*
		 * Speed
		 */
		float targetSpeed = (float) .2;
		
		Block b = e.getFrom().getBlock();
		Material m1 = b.getType();
		Material m2 = b.getRelative(BlockFace.DOWN).getType();
		
	//	targetSpeed *= speeds.get(m1) * speeds.get(m2);
		
		float currentSpeed = p.getWalkSpeed();
		
		if (Math.abs(currentSpeed - targetSpeed) < .01){
			p.setWalkSpeed(targetSpeed);
			return;
		}
		
		p.setWalkSpeed((float) ((0.95 * currentSpeed) + (0.05 * targetSpeed)));
	}
	
	public static void loadConstants(){
		FileConfiguration config = Bukkit.getPluginManager().getPlugin("FastRoads").getConfig();
		expFactor = config.getDouble("expFactor");
		float naturalSoft = (float)config.getDouble("naturalSoft");
		float naturalMedium = (float) config.getDouble("naturalMedium");
		float naturalHard = (float) config.getDouble("naturalHard");
		float artificialSoft = (float) config.getDouble("artificialSoft");
		float artificialMedium = (float) config.getDouble("artificialMedium");
		float artificialHard = (float) config.getDouble("artificialHard");
		
		populateSpeeds(naturalSoft, naturalMedium, naturalHard, artificialSoft, artificialMedium, artificialHard);
	}
	
	/*
	 * isNaturalHard
	 * isNaturalMedium  //default
	 * isNaturalSoft
	 * 
	 * isArtificialHard
	 * isArtificialMedium
	 * isArtificialSoft
	 */
	public static void populateSpeeds(float ns, float nm, float nh, float as, float am, float ah){
		float HARDNESSCATEGORY = nm;
		speeds.put(Material.AIR, HARDNESSCATEGORY);
		speeds.put(Material.STONE, HARDNESSCATEGORY);
		speeds.put(Material.GRASS, HARDNESSCATEGORY);
		speeds.put(Material.DIRT, HARDNESSCATEGORY);
		speeds.put(Material.COBBLESTONE, HARDNESSCATEGORY);
		speeds.put(Material.WOOD, HARDNESSCATEGORY);
		speeds.put(Material.SAPLING, HARDNESSCATEGORY);
		speeds.put(Material.BEDROCK, HARDNESSCATEGORY);
		speeds.put(Material.WATER, HARDNESSCATEGORY);
		speeds.put(Material.STATIONARY_WATER, HARDNESSCATEGORY);
		speeds.put(Material.LAVA, HARDNESSCATEGORY);
		speeds.put(Material.STATIONARY_LAVA, HARDNESSCATEGORY);
		speeds.put(Material.SAND, HARDNESSCATEGORY);
		speeds.put(Material.GRAVEL, HARDNESSCATEGORY);
		speeds.put(Material.GOLD_ORE, HARDNESSCATEGORY);
		speeds.put(Material.IRON_ORE, HARDNESSCATEGORY);
		speeds.put(Material.COAL_ORE, HARDNESSCATEGORY);
		speeds.put(Material.LOG, HARDNESSCATEGORY);
		speeds.put(Material.LEAVES, HARDNESSCATEGORY);
		speeds.put(Material.SPONGE, HARDNESSCATEGORY);
		speeds.put(Material.GLASS, HARDNESSCATEGORY);
		speeds.put(Material.LAPIS_ORE, HARDNESSCATEGORY);
		speeds.put(Material.LAPIS_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.DISPENSER, HARDNESSCATEGORY);
		speeds.put(Material.SANDSTONE, HARDNESSCATEGORY);
		speeds.put(Material.NOTE_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.BED_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.POWERED_RAIL, HARDNESSCATEGORY);
		speeds.put(Material.DETECTOR_RAIL, HARDNESSCATEGORY);
		speeds.put(Material.PISTON_STICKY_BASE, HARDNESSCATEGORY);
		speeds.put(Material.WEB, HARDNESSCATEGORY);
		speeds.put(Material.LONG_GRASS, HARDNESSCATEGORY);
		speeds.put(Material.DEAD_BUSH, HARDNESSCATEGORY);
		speeds.put(Material.PISTON_BASE, HARDNESSCATEGORY);
		speeds.put(Material.PISTON_EXTENSION, HARDNESSCATEGORY);
		speeds.put(Material.WOOL, HARDNESSCATEGORY);
		speeds.put(Material.PISTON_MOVING_PIECE, HARDNESSCATEGORY);
		speeds.put(Material.YELLOW_FLOWER, HARDNESSCATEGORY);
		speeds.put(Material.RED_ROSE, HARDNESSCATEGORY);
		speeds.put(Material.BROWN_MUSHROOM, HARDNESSCATEGORY);
		speeds.put(Material.RED_MUSHROOM, HARDNESSCATEGORY);
		speeds.put(Material.GOLD_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.IRON_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.DOUBLE_STEP, HARDNESSCATEGORY);
		speeds.put(Material.STEP, HARDNESSCATEGORY);
		speeds.put(Material.BRICK, HARDNESSCATEGORY);
		speeds.put(Material.TNT, HARDNESSCATEGORY);
		speeds.put(Material.BOOKSHELF, HARDNESSCATEGORY);
		speeds.put(Material.MOSSY_COBBLESTONE, HARDNESSCATEGORY);
		speeds.put(Material.OBSIDIAN, HARDNESSCATEGORY);
		speeds.put(Material.TORCH, HARDNESSCATEGORY);
		speeds.put(Material.FIRE, HARDNESSCATEGORY);
		speeds.put(Material.MOB_SPAWNER, HARDNESSCATEGORY);
		speeds.put(Material.WOOD_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.CHEST, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_WIRE, HARDNESSCATEGORY);
		speeds.put(Material.DIAMOND_ORE, HARDNESSCATEGORY);
		speeds.put(Material.DIAMOND_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.WORKBENCH, HARDNESSCATEGORY);
		speeds.put(Material.CROPS, HARDNESSCATEGORY);
		speeds.put(Material.SOIL, HARDNESSCATEGORY);
		speeds.put(Material.FURNACE, HARDNESSCATEGORY);
		speeds.put(Material.BURNING_FURNACE, HARDNESSCATEGORY);
		speeds.put(Material.SIGN_POST, HARDNESSCATEGORY);
		speeds.put(Material.WOODEN_DOOR, HARDNESSCATEGORY);
		speeds.put(Material.LADDER, HARDNESSCATEGORY);
		speeds.put(Material.RAILS, HARDNESSCATEGORY);
		speeds.put(Material.COBBLESTONE_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.WALL_SIGN, HARDNESSCATEGORY);
		speeds.put(Material.LEVER, HARDNESSCATEGORY);
		speeds.put(Material.STONE_PLATE, HARDNESSCATEGORY);
		speeds.put(Material.IRON_DOOR_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.WOOD_PLATE, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_ORE, HARDNESSCATEGORY);
		speeds.put(Material.GLOWING_REDSTONE_ORE, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_TORCH_OFF, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_TORCH_ON, HARDNESSCATEGORY);
		speeds.put(Material.STONE_BUTTON, HARDNESSCATEGORY);
		speeds.put(Material.SNOW, HARDNESSCATEGORY);
		speeds.put(Material.ICE, HARDNESSCATEGORY);
		speeds.put(Material.SNOW_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.CACTUS, HARDNESSCATEGORY);
		speeds.put(Material.CLAY, HARDNESSCATEGORY);
		speeds.put(Material.SUGAR_CANE_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.JUKEBOX, HARDNESSCATEGORY);
		speeds.put(Material.FENCE, HARDNESSCATEGORY);
		speeds.put(Material.PUMPKIN, HARDNESSCATEGORY);
		speeds.put(Material.NETHERRACK, HARDNESSCATEGORY);
		speeds.put(Material.SOUL_SAND, HARDNESSCATEGORY);
		speeds.put(Material.GLOWSTONE, HARDNESSCATEGORY);
		speeds.put(Material.PORTAL, HARDNESSCATEGORY);
		speeds.put(Material.JACK_O_LANTERN, HARDNESSCATEGORY);
		speeds.put(Material.CAKE_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.DIODE_BLOCK_OFF, HARDNESSCATEGORY);
		speeds.put(Material.DIODE_BLOCK_ON, HARDNESSCATEGORY);
		speeds.put(Material.LOCKED_CHEST, HARDNESSCATEGORY);
		speeds.put(Material.TRAP_DOOR, HARDNESSCATEGORY);
		speeds.put(Material.MONSTER_EGGS, HARDNESSCATEGORY);
		speeds.put(Material.SMOOTH_BRICK, HARDNESSCATEGORY);
		speeds.put(Material.HUGE_MUSHROOM_1, HARDNESSCATEGORY);
		speeds.put(Material.HUGE_MUSHROOM_2, HARDNESSCATEGORY);
		speeds.put(Material.IRON_FENCE, HARDNESSCATEGORY);
		speeds.put(Material.THIN_GLASS, HARDNESSCATEGORY);
		speeds.put(Material.MELON_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.PUMPKIN_STEM, HARDNESSCATEGORY);
		speeds.put(Material.MELON_STEM, HARDNESSCATEGORY);
		speeds.put(Material.VINE, HARDNESSCATEGORY);
		speeds.put(Material.FENCE_GATE, HARDNESSCATEGORY);
		speeds.put(Material.BRICK_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.SMOOTH_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.MYCEL, HARDNESSCATEGORY);
		speeds.put(Material.WATER_LILY, HARDNESSCATEGORY);
		speeds.put(Material.NETHER_BRICK, HARDNESSCATEGORY);
		speeds.put(Material.NETHER_FENCE, HARDNESSCATEGORY);
		speeds.put(Material.NETHER_BRICK_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.NETHER_WARTS, HARDNESSCATEGORY);
		speeds.put(Material.ENCHANTMENT_TABLE, HARDNESSCATEGORY);
		speeds.put(Material.BREWING_STAND, HARDNESSCATEGORY);
		speeds.put(Material.CAULDRON, HARDNESSCATEGORY);
		speeds.put(Material.ENDER_PORTAL, HARDNESSCATEGORY);
		speeds.put(Material.ENDER_PORTAL_FRAME, HARDNESSCATEGORY);
		speeds.put(Material.ENDER_STONE, HARDNESSCATEGORY);
		speeds.put(Material.DRAGON_EGG, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_LAMP_OFF, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_LAMP_ON, HARDNESSCATEGORY);
		speeds.put(Material.WOOD_DOUBLE_STEP, HARDNESSCATEGORY);
		speeds.put(Material.WOOD_STEP, HARDNESSCATEGORY);
		speeds.put(Material.COCOA, HARDNESSCATEGORY);
		speeds.put(Material.SANDSTONE_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.EMERALD_ORE, HARDNESSCATEGORY);
		speeds.put(Material.ENDER_CHEST, HARDNESSCATEGORY);
		speeds.put(Material.TRIPWIRE_HOOK, HARDNESSCATEGORY);
		speeds.put(Material.TRIPWIRE, HARDNESSCATEGORY);
		speeds.put(Material.EMERALD_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.SPRUCE_WOOD_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.BIRCH_WOOD_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.JUNGLE_WOOD_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.COMMAND, HARDNESSCATEGORY);
		speeds.put(Material.BEACON, HARDNESSCATEGORY);
		speeds.put(Material.COBBLE_WALL, HARDNESSCATEGORY);
		speeds.put(Material.FLOWER_POT, HARDNESSCATEGORY);
		speeds.put(Material.CARROT, HARDNESSCATEGORY);
		speeds.put(Material.POTATO, HARDNESSCATEGORY);
		speeds.put(Material.WOOD_BUTTON, HARDNESSCATEGORY);
		speeds.put(Material.SKULL, HARDNESSCATEGORY);
		speeds.put(Material.ANVIL, HARDNESSCATEGORY);
		speeds.put(Material.TRAPPED_CHEST, HARDNESSCATEGORY);
		speeds.put(Material.GOLD_PLATE, HARDNESSCATEGORY);
		speeds.put(Material.IRON_PLATE, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_COMPARATOR_OFF, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_COMPARATOR_ON, HARDNESSCATEGORY);
		speeds.put(Material.DAYLIGHT_DETECTOR, HARDNESSCATEGORY);
		speeds.put(Material.REDSTONE_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.QUARTZ_ORE, HARDNESSCATEGORY);
		speeds.put(Material.HOPPER, HARDNESSCATEGORY);
		speeds.put(Material.QUARTZ_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.QUARTZ_STAIRS, HARDNESSCATEGORY);
		speeds.put(Material.ACTIVATOR_RAIL, HARDNESSCATEGORY);
		speeds.put(Material.DROPPER, HARDNESSCATEGORY);
		speeds.put(Material.STAINED_CLAY, HARDNESSCATEGORY);
		speeds.put(Material.HAY_BLOCK, HARDNESSCATEGORY);
		speeds.put(Material.CARPET, HARDNESSCATEGORY);
		speeds.put(Material.HARD_CLAY, HARDNESSCATEGORY);
		speeds.put(Material.COAL_BLOCK, HARDNESSCATEGORY);
	}
}

