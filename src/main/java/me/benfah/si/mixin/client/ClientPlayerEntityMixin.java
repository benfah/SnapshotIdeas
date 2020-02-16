package me.benfah.si.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.benfah.si.entity.ReinforcedBoatEntity;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends Entity
{

	public ClientPlayerEntityMixin(EntityType<?> type, World world) {
		super(null, null);
	}

	@Inject(method = "tickRiding", at = @At(value = "TAIL"))
	public void onTickRiding(CallbackInfo info)
	{
		if (this.getVehicle() instanceof ReinforcedBoatEntity) {
			ReinforcedBoatEntity boatEntity = (ReinforcedBoatEntity)this.getVehicle();
	         boatEntity.setInputs(this.input.pressingLeft, this.input.pressingRight, this.input.pressingForward, this.input.pressingBack);
	         this.riding |= this.input.pressingLeft || this.input.pressingRight || this.input.pressingForward || this.input.pressingBack;
	      }
	}
	
	
	
	@Shadow
	public boolean riding;
	
	@Shadow
	public Input input;
	
	
}
