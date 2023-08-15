package net.ludocrypt.joisevis.modules;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleCellGen;
import com.sudoplay.joise.module.ModuleCellular;

import net.ludocrypt.joisevis.ConfigHelper;

public class CellularModuleConfigScreen extends GenericConfigScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("seed", "Seed:", Long.MIN_VALUE, Long.MAX_VALUE, 1L, 1337L, controlPanel, configValues);
		ConfigHelper.addConfigOption("coofA", "Coefficient F1:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.01F, 1.0F, controlPanel, configValues);
		ConfigHelper.addConfigOption("coofB", "Coefficient F2:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.01F, 0.0F, controlPanel, configValues);
		ConfigHelper.addConfigOption("coofC", "Coefficient F3:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.01F, 0.0F, controlPanel, configValues);
		ConfigHelper.addConfigOption("coofD", "Coefficient F4:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.01F, 0.0F, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleCellGen cellGen = new ModuleCellGen();
		cellGen.setSeed(getLong("seed"));
		ModuleCellular moduleCellular = new ModuleCellular(cellGen);
		moduleCellular.setCoefficients(getFloat("coofA"), getFloat("coofB"), getFloat("coofC"), getFloat("coofD"));

		return moduleCellular;
	}

	@Override
	public String getName() {
		return "Cellular";
	}

	@Override
	public GenericConfigScreen copy() {
		return new CellularModuleConfigScreen();
	}

}
