package me.benfah.si.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.benfah.si.block.entity.SoulFlaskBlockEntity;
import me.benfah.si.compat.IFlaskProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity implements IFlaskProvider {

	BlockPos activeFlask;

	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "stopRiding", at = @At(value = "TAIL"), cancellable = true)
	public void stopRiding(CallbackInfo info) {
		ridingCooldown = 10;
	}

	@Override
	public void setActiveFlask(BlockPos pos) {
		activeFlask = pos;
	}

	@Override
	public BlockPos getActiveFlask() {
		return activeFlask;
	}

	@Override
	public boolean canRespawnAtFlask() {
		if (activeFlask != null && this.world.getBlockEntity(activeFlask) != null) {
			BlockEntity blockEntity = world.getBlockEntity(activeFlask);
			if (blockEntity instanceof SoulFlaskBlockEntity) {
				SoulFlaskBlockEntity flaskBlockEntity = (SoulFlaskBlockEntity) blockEntity;
				return flaskBlockEntity.isOccupied() && flaskBlockEntity.getPlayerUuid().equals(this.getUuid());
			}
		}

		return false;
	}

	@Inject(at = @At("TAIL"), method = "readCustomDataFromTag")
	private void onReadCustomData(CompoundTag tag, CallbackInfo info) {
		if (tag.contains("FlaskX") && tag.contains("FlaskY") && tag.contains("FlaskZ")) {
			activeFlask = new BlockPos(tag.getInt("FlaskX"), tag.getInt("FlaskX"), tag.getInt("FlaskX"));
		}
	}

	@Inject(at = @At("TAIL"), method = "writeCustomDataToTag")
	private void onWriteCustomData(CompoundTag tag, CallbackInfo info) {
		if (activeFlask != null) {
			tag.putInt("FlaskX", activeFlask.getX());
			tag.putInt("FlaskY", activeFlask.getY());
			tag.putInt("FlaskZ", activeFlask.getZ());
		}
	}

	@Shadow
	public void addMessage(Text message, boolean actionBar) {
	}
}
