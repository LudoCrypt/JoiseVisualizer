package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModulePow;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModulePowConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModulePow modulePow = new ModulePow();

		modulePow.setSource(getManipulatingModule());
		modulePow.setPower(getManipulatingModule(1));

		return modulePow;
	}

	@Override
	public String getName() {
		return "Pow";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModulePowConfigScreen();
	}

	@Override
	public int manipulatorCount() {
		return 2;
	}

}
