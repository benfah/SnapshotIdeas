package me.benfah.si.init;

import me.benfah.si.SnapshotIdeas;
import me.benfah.si.block.SIStairsBlock;
import me.benfah.si.block.SoulFlaskBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SIBlocks {
	
	public static SoulFlaskBlock SOUL_FLASK_BLOCK = new SoulFlaskBlock(FabricBlockSettings.of(Material.GLASS).lightLevel(14).nonOpaque().build());
	
	public static StairsBlock BASALT_STAIRS_BLOCK = new SIStairsBlock(Blocks.BASALT.getDefaultState(), Block.Settings.copy(Blocks.BASALT));
	public static SlabBlock BASALT_SLAB_BLOCK = new SlabBlock(Block.Settings.copy(Blocks.BASALT));
	
	public static Block POLISHED_BASALT_BLOCK = new Block(Block.Settings.copy(Blocks.BASALT));
	public static StairsBlock POLISHED_BASALT_STAIRS_BLOCK = new SIStairsBlock(Blocks.BASALT.getDefaultState(), Block.Settings.copy(Blocks.BASALT));
	public static SlabBlock POLISHED_BASALT_SLAB_BLOCK = new SlabBlock(Block.Settings.copy(Blocks.BASALT));
	
	
	public static void init() {
		register("soul_flask", SOUL_FLASK_BLOCK);
		
		register("basalt_stairs", BASALT_STAIRS_BLOCK);
		register("basalt_slab", BASALT_SLAB_BLOCK);
		
		register("polished_basalt", POLISHED_BASALT_BLOCK);
		register("polished_basalt_stairs", POLISHED_BASALT_STAIRS_BLOCK);
		register("polished_basalt_slab", POLISHED_BASALT_SLAB_BLOCK);
	}
	
	public static void register(String name, Block block)
	{
		Registry.register(Registry.BLOCK, new Identifier(SnapshotIdeas.MOD_ID, name), block);
	}
	
}
