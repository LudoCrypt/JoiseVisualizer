package net.ludocrypt.joisevis;

import com.sudoplay.joise.ModuleInstanceMap;
import com.sudoplay.joise.ModuleMap;
import com.sudoplay.joise.ModulePropertyMap;
import com.sudoplay.joise.module.Module;

public class ConstantModule extends com.sudoplay.joise.module.Module {

	double constant;

	public ConstantModule(double constant) {
		this.constant = constant;
	}

	@Override
	public double get(double x, double y) {
		return constant;
	}

	@Override
	public double get(double x, double y, double z) {
		return constant;
	}

	@Override
	public double get(double x, double y, double z, double w) {
		return constant;
	}

	@Override
	public double get(double x, double y, double z, double w, double u, double v) {
		return constant;
	}

	@Override
	public void setSeed(String seedName, long seed) {

	}

	@Override
	public void writeToMap(ModuleMap moduleMap) {
		ModulePropertyMap modulePropertyMap = new ModulePropertyMap(this);
		modulePropertyMap.writeDouble("constant", this.constant);
		moduleMap.put(this.getId(), modulePropertyMap);
	}

	@Override
	public Module buildFromPropertyMap(ModulePropertyMap modulePropertyMap, ModuleInstanceMap moduleInstanceMap) {
		constant = (modulePropertyMap.readDouble("constant"));
		return this;
	}

}
