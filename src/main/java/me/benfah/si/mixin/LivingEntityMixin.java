package me.benfah.si.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.google.common.collect.ImmutableSet;

import me.benfah.si.entity.ReinforcedBoatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WeepingVinesPlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "onDismounted", at = @At(value = "HEAD"), cancellable = true)
	public void onOnDismounted(Entity vehicle, CallbackInfo info) {
		if (vehicle instanceof ReinforcedBoatEntity) {
			handleDismount(vehicle, this, getMainArm());
			info.cancel();
		}

	}

	@Inject(method = "isClimbing", at = @At(value = "RETURN", ordinal = 2), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
	public void onIsClimbing(CallbackInfoReturnable<Boolean> info, BlockState state, Block block) {
		if (block instanceof WeepingVinesPlantBlock)
			info.setReturnValue(true);
	}

	private static void handleDismount(Entity vehicle, Entity base, Arm arm) {
		double aa;
		double ab;
		double ac;
		int ad;
		if (vehicle instanceof ReinforcedBoatEntity) {
			double d = (double) (base.getWidth() / 2.0F + vehicle.getWidth() / 2.0F) + 0.4D;
			Box box = vehicle.getBoundingBox();
			float h = 0;
			double g = box.y2;
			byte j = 2;
			float k = -base.yaw * 0.017453292F - 3.1415927F + h;
			float l = -MathHelper.sin(k);
			float m = -MathHelper.cos(k);
			aa = Math.abs(l) > Math.abs(m) ? d / (double) Math.abs(l) : d / (double) Math.abs(m);
			Box box2 = base.getBoundingBox().offset(-base.getX(), -base.getY(), -base.getZ());
			ImmutableSet<Entity> immutableSet = ImmutableSet.of(base, vehicle);
			ab = base.getX() + (double) l * aa;
			ac = base.getZ() + (double) m * aa;
			double q = 0.001D;

			for (ad = 0; ad < j; ++ad) {
				double s = g + q;
				if (base.world.doesNotCollide(base, box2.offset(ab, s, ac), immutableSet)) {
					base.updatePosition(ab, s, ac);
					return;
				}

				++q;
			}

			base.updatePosition(vehicle.getX(), vehicle.getBodyY(1.0D) + 0.001D, vehicle.getZ());
		}
	}

	@Shadow
	public Arm getMainArm() {
		return null;
	}

}
