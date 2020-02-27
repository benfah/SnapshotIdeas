package me.benfah.si.init;

import me.benfah.si.entity.ReinforcedBoatEntity;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SIEntities {
	
	public static EntityType<ReinforcedBoatEntity> REINFORCED_BOAT;
	
	public static void init() {
		REINFORCED_BOAT = Registry.register(Registry.ENTITY_TYPE,
				new Identifier("snapshotideas", "reinforced_boat"), FabricEntityTypeBuilder.<ReinforcedBoatEntity>create(EntityCategory.MISC, ReinforcedBoatEntity::new).size(EntityDimensions.fixed(1.375F, 0.5625F)).build());
	}
	
}
