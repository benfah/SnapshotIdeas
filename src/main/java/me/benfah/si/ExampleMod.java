package me.benfah.si;

import me.benfah.si.entity.ReinforcedBoatEntity;
import me.benfah.si.item.ReinforcedBoatItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	
	public static final EntityType<ReinforcedBoatEntity> REINFORCED_BOAT = Registry.register(Registry.ENTITY_TYPE,
			new Identifier("snapshotideas", "reinforced_boat"), FabricEntityTypeBuilder.<ReinforcedBoatEntity>create(EntityCategory.MISC, ReinforcedBoatEntity::new).size(EntityDimensions.fixed(1.375F, 0.5625F)).build());
	
	public static final ReinforcedBoatItem BOAT_ITEM = new ReinforcedBoatItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).fireproof().maxCount(1));
	
	
	public static void init()
	{
		try {
			RenderLayers.class.getDeclaredField("FLUIDS").get(null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onInitialize() {
		
		Registry.register(Registry.ITEM, new Identifier("snapshotideas", "reinforced_boat"), BOAT_ITEM);
		System.out.println(REINFORCED_BOAT);
	}
}
