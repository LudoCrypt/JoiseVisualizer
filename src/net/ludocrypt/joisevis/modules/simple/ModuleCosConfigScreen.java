package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleCos;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleCosConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleCos moduleCos = new ModuleCos();

		moduleCos.setSource(getManipulatingModule());

		return moduleCos;
	}

	@Override
	public String getName() {
		return "Cos";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleCosConfigScreen();
	}

}
