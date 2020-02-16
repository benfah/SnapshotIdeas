package me.benfah.si.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.benfah.si.entity.ReinforcedBoatEntity;
import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class EntityMixin {
	
	@Inject(method = "setOnFireFromLava", at = @At(value = "HEAD"), cancellable = true)
	public void setOnFireFromLava(CallbackInfo info)
	{
		if(getVehicle() instanceof ReinforcedBoatEntity || ridingCooldown > 0)
		info.cancel();
	}
	
	
	@Shadow
	public Entity getVehicle() { return null; }
	
	@Shadow
	public int ridingCooldown;
}
