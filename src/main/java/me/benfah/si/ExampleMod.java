package me.benfah.si;

import me.benfah.si.init.SIBlockEntities;
import me.benfah.si.init.SIBlocks;
import me.benfah.si.init.SIEntities;
import me.benfah.si.init.SIItems;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {
	
	
	
	
	
	
	
	
	@Override
	public void onInitialize() {
		SIBlocks.init();
		SIBlockEntities.init();
		SIEntities.init();
		SIItems.init();
	}
}
