package me.benfah.si;

import java.lang.reflect.Field;
import java.util.Map;

import me.benfah.si.entity.renderer.ReinforcedBoatEntityRenderer;
import me.benfah.si.init.SIBlockEntities;
import me.benfah.si.init.SIBlocks;
import me.benfah.si.init.SIEntities;
import me.benfah.si.network.SINetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.WitherSkullEntityRenderer;
import net.minecraft.scoreboard.ScoreboardCriterion.RenderType;

public class Client implements ClientModInitializer
{
	
	
	@Override
	public void onInitializeClient()
	{
		EntityRendererRegistry.INSTANCE.register(SIEntities.REINFORCED_BOAT, (entityRenderDispatcher, context) -> new ReinforcedBoatEntityRenderer(entityRenderDispatcher));
		BlockRenderLayerMap.INSTANCE.putBlock(SIBlocks.SOUL_FLASK_BLOCK, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(SIBlocks.BASALT_SLAB_BLOCK, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(SIBlocks.BASALT_STAIRS_BLOCK, RenderLayer.getTranslucent());
		SINetworkHandler.init();

	}

}
