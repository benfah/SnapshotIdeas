package me.benfah.si.block.entity;

import java.util.UUID;

import me.benfah.si.ExampleMod;
import me.benfah.si.init.SIBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class SoulFlaskBlockEntity extends BlockEntity {
	
	private UUID playerUuid;
	
	private int x;
	private int y;
	private int z;
	private Direction d = Direction.NORTH;
	

	public SoulFlaskBlockEntity() {
		super(SIBlockEntities.SOUL_FLASK_BLOCK_ENTITY);
	}
	
	public boolean isOccupied()
	{
		return playerUuid != null;
	}
	
	public UUID getPlayerUuid() {
		return playerUuid;
	}
	
	public void clearActivePlayer()
	{
		playerUuid = null;
	}
	
	public void setActivePlayer(PlayerEntity p)
	{
		playerUuid = p.getUuid();
		x = (int) Math.floor(p.getX());
		y = (int) Math.floor(p.getY());
		z = (int) Math.floor(p.getZ());
		d = p.getHorizontalFacing();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Direction getDirection() {
		return d;
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag)
	{
		super.toTag(tag);
		
		if(playerUuid != null)
		{
			tag.putUuidNew("PlayerUuid", playerUuid);
			tag.putInt("PlayerX", x);
			tag.putInt("PlayerY", y);
			tag.putInt("PlayerZ", z);
			tag.putString("PlayerDir", d.asString());
		}
		
		return tag;
	}
	
	@Override
	public void fromTag(CompoundTag tag)
	{
		super.fromTag(tag);
		
		if(tag.contains("PlayerUuid"))
		{
			if(tag.containsUuidOld("PlayerUuid"))
			playerUuid = tag.getUuidOld("PlayerUuid");	
			else
			playerUuid = tag.getUuidNew("PlayerUuid");	

			x = tag.getInt("PlayerX");
			y = tag.getInt("PlayerY");
			z = tag.getInt("PlayerZ");
			d = Direction.byName(tag.getString("PlayerDir"));
		}
	}
	
}
