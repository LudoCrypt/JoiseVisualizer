package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleClamp;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleClampConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("low", "Low:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.1F, 0.0F, controlPanel, configValues);
		ConfigHelper.addConfigOption("high", "High:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.1F, 1.0F, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleClamp moduleClamp = new ModuleClamp();

		moduleClamp.setSource(getManipulatingModule());
		moduleClamp.setLow(getFloat("low"));
		moduleClamp.setHigh(getFloat("high"));

		return moduleClamp;
	}

	@Override
	public String getName() {
		return "Clamp";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleClampConfigScreen();
	}

}
