package net.ludocrypt.joisevis.modules;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleBasisFunction;

import net.ludocrypt.joisevis.ConfigHelper;

public class ModuleBasisConfigScreen extends GenericConfigScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("seed", "Seed:", Long.MIN_VALUE, Long.MAX_VALUE, 1L, 1337L, controlPanel, configValues);
		ConfigHelper.addEnumConfigOption("basis", "Basis:", ModuleBasisFunction.BasisType.values(), ModuleBasisFunction.BasisType.SIMPLEX, controlPanel, configValues);
		ConfigHelper.addEnumConfigOption("interpolation", "Interpolation:", ModuleBasisFunction.InterpolationType.values(), ModuleBasisFunction.InterpolationType.CUBIC, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleBasisFunction gen = new ModuleBasisFunction();
		gen.setSeed(getLong("seed"));
		gen.setInterpolation((ModuleBasisFunction.InterpolationType) configValues.get("interpolation"));
		gen.setType((ModuleBasisFunction.BasisType) configValues.get("basis"));

		return gen;
	}

	@Override
	public String getName() {
		return "Basis";
	}

	@Override
	public GenericConfigScreen copy() {
		return new ModuleBasisConfigScreen();
	}

}
