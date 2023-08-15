package net.ludocrypt.joisevis.modules.simple;

import com.sudoplay.joise.module.Module;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.ConstantModule;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;

public class ModuleConstConfigScreen extends GenericConfigScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("constant", "Const:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.1F, 0.0F, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		return new ConstantModule(getFloat("constant"));
	}

	@Override
	public String getName() {
		return "Constant";
	}

	@Override
	public GenericConfigScreen copy() {
		return new ModuleConstConfigScreen();
	}

}
