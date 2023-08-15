package net.ludocrypt.joisevis.modules.complex;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleAutoCorrect;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleAutoCorrectConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("low", "Low:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.1F, 0.0F, controlPanel, configValues);
		ConfigHelper.addConfigOption("high", "High:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.1F, 1.0F, controlPanel, configValues);

		ConfigHelper.addConfigOption("samples", "Samples:", 1, Integer.MAX_VALUE, 10, 100, controlPanel, configValues);
		ConfigHelper.addConfigOption("sampleScale", "Sample Scale:", 0.0F, Float.POSITIVE_INFINITY, 1.0F, 50.0F, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleAutoCorrect autoCorrect = new ModuleAutoCorrect();

		autoCorrect.setSource(getManipulatingModule());
		autoCorrect.setLow(getFloat("low"));
		autoCorrect.setHigh(getFloat("high"));
		autoCorrect.setSamples(getInt("samples"));
		autoCorrect.setSampleScale(getFloat("sampleScale"));
		autoCorrect.calculate3D();

		return autoCorrect;
	}

	@Override
	public String getName() {
		return "Auto Correct";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleAutoCorrectConfigScreen();
	}

}
