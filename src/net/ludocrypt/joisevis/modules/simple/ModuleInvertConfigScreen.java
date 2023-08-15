package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleInvert;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleInvertConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleInvert moduleInvert = new ModuleInvert();

		moduleInvert.setSource(getManipulatingModule());

		return moduleInvert;
	}

	@Override
	public String getName() {
		return "Invert";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleInvertConfigScreen();
	}

}
