package net.ludocrypt.joisevis.modules.complex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleCombiner;

import net.ludocrypt.joisevis.ConfigHelper;
import net.ludocrypt.joisevis.ConfigScrollPane;
import net.ludocrypt.joisevis.EmptyModule;
import net.ludocrypt.joisevis.Main;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;

public class ModuleCombinerConfigScreen extends GenericConfigScreen {

	List<JPanel> currentPanels = new ArrayList<JPanel>();

	@Override
	public void addOptions() {

		ConfigHelper.addEnumConfigOption("type", "Type", ModuleCombiner.CombinerType.values(), ModuleCombiner.CombinerType.ADD, controlPanel, configValues);

		JSpinner sourcesConfig = ConfigHelper.addConfigOption("sourceCount", "Sources:", 1, 10, 1, 2, controlPanel, configValues);

		JPanel sourcePanel = new JPanel();
		sourcePanel.setLayout(new BoxLayout(sourcePanel, BoxLayout.Y_AXIS));

		JScrollPane sourceScrollPane = new JScrollPane(sourcePanel);
		sourceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		sourcesConfig.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				Component[] currComponents = sourcePanel.getComponents().clone();

				for (int i = (int) sourcesConfig.getValue(); i < currComponents.length; i++) {
					Component component = currComponents[i];
					Main.hoveringGrabComponents.remove(component);
					sourcePanel.remove(component);
					currentPanels.remove(component);
				}
				if ((int) sourcesConfig.getValue() > currComponents.length) {
					for (int i = 0; i < (int) sourcesConfig.getValue() - currComponents.length; i++) {
						JPanel panel = new JPanel(new GridLayout()) {
							@Override
							protected void paintComponent(Graphics g) {
								super.paintComponent(g);

								// Draw the red outline
								g.setColor(Color.RED);
								for (int i = 0; i < 2; i++) {
									g.drawRect(i, i, getWidth() - 1 - 2 * i, getHeight() - 1 - 2 * i);
								}
							}
						};
						Main.hoveringGrabComponents.add(panel);
						panel.setPreferredSize(new Dimension(0, 200));
						panel.setMinimumSize(new Dimension(20, 50));

						sourcePanel.add(panel);
						currentPanels.add(panel);
					}
				}

				sourcePanel.revalidate();
				sourcePanel.repaint();
			}

		});

		sourcesConfig.getChangeListeners()[0].stateChanged(new ChangeEvent(sourcesConfig));
		controlPanel.add(sourceScrollPane);

	}

	@Override
	public int width() {
		return 300;
	}

	@Override
	public int height() {
		return 200;
	}

	@Override
	public boolean pack() {
		return false;
	}

	@Override
	public String getName() {
		return "Combine";
	}

	@Override
	public GenericConfigScreen copy() {
		return new ModuleCombinerConfigScreen();
	}

	@Override
	public Module getModule() {
		ModuleCombiner module = new ModuleCombiner();
		module.setType((ModuleCombiner.CombinerType) configValues.get("type"));

		int sourcesCount = getInt("sourceCount");

		for (int i = 0; i < sourcesCount; i++) {
			module.setSource(i, getManipulatingModule(i));
		}

		return module;
	}

	public Module getManipulatingModule(int index) {
		for (Component component : currentPanels.get(index).getComponents()) {
			if (component instanceof ConfigScrollPane configScrollPane) {
				return configScrollPane.configScreen.getModule();
			}
		}

		return new EmptyModule();
	}

}
