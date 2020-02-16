package me.benfah.si.network;

import java.util.UUID;

import me.benfah.si.ExampleMod;
import me.benfah.si.entity.ReinforcedBoatEntity;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class SINetworkHandler
{
	
	private SINetworkHandler()
	{
	}
	
	public static void init()
	{
		registerBoatPacket();
	}
	
	public static void registerBoatPacket()
	{
		ClientSidePacketRegistry.INSTANCE.register(ReinforcedBoatEntity.ENTITY_ID, (packetContext, packetByteBuf) ->
		{
			double x = packetByteBuf.readDouble();
			double y = packetByteBuf.readDouble();
			double z = packetByteBuf.readDouble();
			
			float yaw = packetByteBuf.readFloat();
			float pitch = packetByteBuf.readFloat();

			int entityId = packetByteBuf.readInt();
			UUID uuid = packetByteBuf.readUuid();
			packetContext.getTaskQueue().execute(() ->
			{
				ReinforcedBoatEntity entity = new ReinforcedBoatEntity(MinecraftClient.getInstance().world, x, y, z);
				entity.updateTrackedPosition(x, y, z);
		        entity.pitch = pitch;
		        entity.yaw = yaw;
		        entity.setEntityId(entityId);
		        entity.setUuid(uuid);
				MinecraftClient.getInstance().world.addEntity(entityId, entity);
			});
		});
	}
	
	
}
