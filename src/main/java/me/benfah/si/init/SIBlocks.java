package me.benfah.si.init;

import me.benfah.si.block.SoulFlaskBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class SIBlocks {
	
	public static SoulFlaskBlock SOUL_FLASK_BLOCK = new SoulFlaskBlock(FabricBlockSettings.of(Material.GLASS).lightLevel(14).nonOpaque().build());
	
	public static void init() {
		Registry.register(Registry.BLOCK, "snapshotideas:soul_flask", SOUL_FLASK_BLOCK);
	}
	

	
}
