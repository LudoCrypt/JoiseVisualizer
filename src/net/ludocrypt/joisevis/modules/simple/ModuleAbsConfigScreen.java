package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleAbs;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleAbsConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleAbs moduleAbs = new ModuleAbs();

		moduleAbs.setSource(getManipulatingModule());

		return moduleAbs;
	}

	@Override
	public String getName() {
		return "Absolute Value";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleAbsConfigScreen();
	}

}
