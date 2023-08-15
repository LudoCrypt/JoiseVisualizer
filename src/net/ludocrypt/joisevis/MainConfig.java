package net.ludocrypt.joisevis;

import com.sudoplay.joise.module.Module;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;

public class MainConfig extends GenericConfigScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("width", "Width:", 0, 2048, 8, 256, controlPanel, configValues);
		ConfigHelper.addConfigOption("height", "Height:", 0, 2048, 8, 256, controlPanel, configValues);
	}

	@Override
	public boolean important() {
		return true;
	}

	@Override
	public int width() {
		return 300;
	}

	@Override
	public int height() {
		return 206;
	}

	@Override
	public boolean pack() {
		return false;
	}

	@Override
	public Module getModule() {
		return new EmptyModule();
	}

	@Override
	public String getName() {
		return "Base";
	}

	@Override
	public GenericConfigScreen copy() {
		return null;
	}

}
