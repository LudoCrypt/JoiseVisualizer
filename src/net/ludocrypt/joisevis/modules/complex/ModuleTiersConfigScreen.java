package net.ludocrypt.joisevis.modules.complex;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleTiers;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleTiersConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addCheckboxConfigOption("smooth", "Smooth?", false, controlPanel, configValues);
		ConfigHelper.addConfigOption("tiers", "Tiers:", 0, Integer.MAX_VALUE, 1, 5, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleTiers moduleTiers = new ModuleTiers();

		moduleTiers.setSource(getManipulatingModule());
		moduleTiers.setNumTiers(getInt("tiers"));

		return moduleTiers;
	}

	@Override
	public String getName() {
		return "Tiers";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleTiersConfigScreen();
	}

}
