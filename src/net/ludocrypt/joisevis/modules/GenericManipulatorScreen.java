package net.ludocrypt.joisevis.modules;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sudoplay.joise.module.Module;

import net.ludocrypt.joisevis.ConfigScrollPane;
import net.ludocrypt.joisevis.EmptyModule;
import net.ludocrypt.joisevis.Main;

public abstract class GenericManipulatorScreen extends GenericConfigScreen<GenericManipulatorScreen> {

	List<JPanel> manipulators = new ArrayList<JPanel>();

	@Override
	public void createUI() {
		super.createUI();

		JPanel paddingPanel = new JPanel();
		paddingPanel.setPreferredSize(new Dimension(0, 20));
		paddingPanel.setMaximumSize(new Dimension(20, 20));
		controlPanel.add(paddingPanel);

		for (int i = 0; i < manipulatorCount(); i++) {
			JPanel manipulatePanel = getManipulationPanel(i);
			Main.hoveringGrabComponents.add(manipulatePanel);
			manipulatePanel.setPreferredSize(new Dimension(0, 200));
			manipulatePanel.setMinimumSize(new Dimension(20, 50));

			controlPanel.add(manipulatePanel);
			manipulators.add(manipulatePanel);
		}
	}

	public Module getManipulatingModule() {
		return getManipulatingModule(0);
	}

	public Module getManipulatingModule(int index) {
		for (Component component : manipulators.get(index).getComponents()) {
			if (component instanceof ConfigScrollPane configScrollPane) {
				return configScrollPane.configScreen.getModule();
			}
		}

		return new EmptyModule();
	}

	public int manipulatorCount() {
		return 1;
	}

	public JPanel getManipulationPanel(int manipulator) {
		return new JPanel(new GridLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.setColor(Color.RED);
				for (int i = 0; i < 2; i++) {
					g.drawRect(i, i, getWidth() - 1 - 2 * i, getHeight() - 1 - 2 * i);
				}
			}
		};
	}

	@Override
	public GenericManipulatorScreen cloneScreen() {
		GenericManipulatorScreen copy = super.cloneScreen();

		for (int i = 0; i < manipulatorCount(); i++) {
			for (Component component : this.manipulators.get(i).getComponents()) {
				if (component instanceof ConfigScrollPane configScrollPane) {
					GenericConfigScreen<?> configScreen = configScrollPane.configScreen.cloneScreen();

					int b = i;
					SwingUtilities.invokeLater(() -> {
						configScreen.configFrame.setVisible(false);
						copy.manipulators.get(b).add(configScreen.scrollPane);
					});

					continue;
				}
			}
		}

		return copy;
	}

	public abstract GenericManipulatorScreen copy();

}
