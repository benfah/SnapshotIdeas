package me.benfah.si.init;

import me.benfah.si.item.ReinforcedBoatItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SIItems {
	
	public static ReinforcedBoatItem BOAT_ITEM = new ReinforcedBoatItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).fireproof().maxCount(1));
	
	
	public static BlockItem BASALT_SLAB_ITEM;
	public static BlockItem BASALT_STAIRS_ITEM;
	
	public static BlockItem POLISHED_BASALT_ITEM;
	public static BlockItem POLISHED_BASALT_SLAB_ITEM;
	public static BlockItem POLISHED_BASALT_STAIRS_ITEM;
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier("snapshotideas:reinforced_boat"), BOAT_ITEM);
		
		BASALT_SLAB_ITEM = registerBlockItem(SIBlocks.BASALT_SLAB_BLOCK);
		BASALT_STAIRS_ITEM = registerBlockItem(SIBlocks.BASALT_STAIRS_BLOCK);
		
		POLISHED_BASALT_ITEM = registerBlockItem(SIBlocks.POLISHED_BASALT_BLOCK);
		POLISHED_BASALT_SLAB_ITEM = registerBlockItem(SIBlocks.POLISHED_BASALT_SLAB_BLOCK);
		POLISHED_BASALT_STAIRS_ITEM = registerBlockItem(SIBlocks.POLISHED_BASALT_STAIRS_BLOCK);

	}
	
	public static BlockItem registerBlockItem(Block block)
	{
		BlockItem blockItem = new BlockItem(block, new Settings().group(ItemGroup.DECORATIONS));
		Identifier identifier = Registry.BLOCK.getId(block);
		return Registry.register(Registry.ITEM, identifier, blockItem);
	}
	
}
