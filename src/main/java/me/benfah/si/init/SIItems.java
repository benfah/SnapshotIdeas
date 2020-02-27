package me.benfah.si.init;

import me.benfah.si.item.ReinforcedBoatItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SIItems {
	
	public static ReinforcedBoatItem BOAT_ITEM = new ReinforcedBoatItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).fireproof().maxCount(1));
	public static BlockItem SOUL_FLASK_ITEM = new BlockItem(SIBlocks.SOUL_FLASK_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));
	
	
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier("snapshotideas", "reinforced_boat"), BOAT_ITEM);
		Registry.register(Registry.ITEM, new Identifier("snapshotideas", "soul_flask"), SOUL_FLASK_ITEM);
	}

}
