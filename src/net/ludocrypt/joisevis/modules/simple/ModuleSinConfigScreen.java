package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleSin;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleSinConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleSin moduleSin = new ModuleSin();

		moduleSin.setSource(getManipulatingModule());

		return moduleSin;
	}

	@Override
	public String getName() {
		return "Sin";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleSinConfigScreen();
	}

}
