package me.benfah.si.compat;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IFlaskProvider
{
	
	public void setActiveFlask(BlockPos pos);
	
	public BlockPos getActiveFlask();
	
	public boolean canRespawnAtFlask();
	
}
