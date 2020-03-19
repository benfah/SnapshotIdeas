package me.benfah.si;

import me.benfah.si.init.SIBlocks;
import me.benfah.si.init.SIEntities;
import me.benfah.si.init.SIItems;
import net.fabricmc.api.ModInitializer;

public class SnapshotIdeas implements ModInitializer {
	
	public static final String MOD_ID = "snapshotideas";
	
	
	
	
	
	
	@Override
	public void onInitialize() {
		SIBlocks.init();
		SIEntities.init();
		SIItems.init();
	}
}
