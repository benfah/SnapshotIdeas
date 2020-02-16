package me.benfah.si;

import me.benfah.si.entity.renderer.ReinforcedBoatEntityRenderer;
import me.benfah.si.network.SINetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.WitherSkullEntityRenderer;

public class Client implements ClientModInitializer
{
	
	
	@Override
	public void onInitializeClient()
	{
		EntityRendererRegistry.INSTANCE.register(ExampleMod.REINFORCED_BOAT, (entityRenderDispatcher, context) -> new ReinforcedBoatEntityRenderer(entityRenderDispatcher));
		SINetworkHandler.init();
		System.out.println("CLIENT!!!!");
	
	}

}
