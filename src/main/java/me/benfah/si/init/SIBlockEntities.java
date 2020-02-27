package me.benfah.si.init;

import me.benfah.si.block.entity.SoulFlaskBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class SIBlockEntities {
	
	public static BlockEntityType<SoulFlaskBlockEntity> SOUL_FLASK_BLOCK_ENTITY;

	
	public static void init() {
		SOUL_FLASK_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "snapshotideas:soul_flask", BlockEntityType.Builder.<SoulFlaskBlockEntity>create(SoulFlaskBlockEntity::new, SIBlocks.SOUL_FLASK_BLOCK).build(null));

	}

}
