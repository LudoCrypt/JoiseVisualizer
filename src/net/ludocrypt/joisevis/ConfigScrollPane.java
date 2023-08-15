package net.ludocrypt.joisevis;

import java.awt.Component;

import javax.swing.JScrollPane;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;

public class ConfigScrollPane extends JScrollPane {

	public final GenericConfigScreen configScreen;

	public ConfigScrollPane(Component view, GenericConfigScreen configScreen) {
		super(view);
		this.configScreen = configScreen;
	}

}
