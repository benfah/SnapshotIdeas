package me.benfah.si.util;

import net.minecraft.util.math.Direction;

public class DirectionHelper
{
	
	public static float asYaw(Direction d)
	{
		switch(d)
		{
		case NORTH:
			return -180;
			
		case EAST:
			return -90;
			
		case SOUTH:
			return 0;
					
		case WEST:
			return 90;
		
		}
		return 0;
	}
	
	
}
