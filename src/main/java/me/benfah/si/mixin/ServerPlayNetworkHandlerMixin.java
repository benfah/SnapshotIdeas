package me.benfah.si.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import me.benfah.si.compat.IFlaskProvider;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

	@ModifyArgs(method = "onClientStatus", at = @At(value = "INVOKE", target = "net/minecraft/server/PlayerManager.respawnPlayer(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/world/dimension/DimensionType;Z)Lnet/minecraft/server/network/ServerPlayerEntity;"))
	public void onRespawn(Args args) {
		ServerPlayerEntity playerEntity = args.get(0);
		IFlaskProvider provider = (IFlaskProvider) playerEntity;
		if(playerEntity.dimension.equals(DimensionType.THE_NETHER) && provider.canRespawnAtFlask())
		{
			args.set(1, DimensionType.THE_NETHER);
		}
		
	}

}
