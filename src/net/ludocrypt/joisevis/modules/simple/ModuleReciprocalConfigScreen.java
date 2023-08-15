package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModulePow;

import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleReciprocalConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModulePow modulePow = new ModulePow();

		modulePow.setSource(getManipulatingModule());
		modulePow.setPower(-1.0D);

		return modulePow;
	}

	@Override
	public String getName() {
		return "Reciprocal";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleReciprocalConfigScreen();
	}

}
