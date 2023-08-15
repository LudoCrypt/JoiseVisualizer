package net.ludocrypt.joisevis.modules.complex;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleFunctionGradient;
import com.sudoplay.joise.module.ModuleFunctionGradient.FunctionGradientAxis;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleFunctionGradientConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("spacing", "Spacing:", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.01F, 0.0F, controlPanel, configValues);
		ConfigHelper.addEnumConfigOption("functionGradientAxis", "Gradient Axis:", FunctionGradientAxis.values(), FunctionGradientAxis.X_AXIS, controlPanel, configValues);
	}

	@Override
	public Module getModule() {
		ModuleFunctionGradient moduleFunctionGradient = new ModuleFunctionGradient();
		moduleFunctionGradient.setSource(getManipulatingModule());
		moduleFunctionGradient.setAxis((FunctionGradientAxis) configValues.get("functionGradientAxis"));
		moduleFunctionGradient.setSpacing(getFloat("spacing"));

		return moduleFunctionGradient;
	}

	@Override
	public String getName() {
		return "Function Gradient";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleFunctionGradientConfigScreen();
	}

}
