package me.benfah.si.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import me.benfah.si.block.entity.SoulFlaskBlockEntity;
import me.benfah.si.compat.IFlaskProvider;
import me.benfah.si.util.DirectionHelper;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

	

	@Inject(method = "respawnPlayer", at = @At(value = "NEW", target = "net/minecraft/network/packet/s2c/play/PlayerRespawnS2CPacket"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void onRespawnPlayer(ServerPlayerEntity player, DimensionType dimension, boolean alive,
			CallbackInfoReturnable<ServerPlayerEntity> infoServerPlayerEntity, BlockPos pos, boolean bool,
			ServerPlayerInteractionManager serverPlayerInteractionManager2, ServerPlayerEntity serverPlayerEntity) {
		IFlaskProvider oldProvider = (IFlaskProvider) player;
		IFlaskProvider provider = (IFlaskProvider) serverPlayerEntity;
		provider.setActiveFlask(oldProvider.getActiveFlask());
		if (provider.canRespawnAtFlask()) {
			SoulFlaskBlockEntity blockEntity = (SoulFlaskBlockEntity) player.world.getBlockEntity(provider.getActiveFlask());
			
			serverPlayerEntity.setPos(blockEntity.getX() + 0.5, blockEntity.getY(),
					blockEntity.getZ() + 0.5);
			
			serverPlayerEntity.yaw = DirectionHelper.asYaw(blockEntity.getDirection());
		}
		else if(provider.getActiveFlask() != null)
		{
			serverPlayerEntity.addMessage(new TranslatableText("block.snapshotideas.soul_flask.obstructed"), false);
			provider.setActiveFlask(null);
		}
	}
}
