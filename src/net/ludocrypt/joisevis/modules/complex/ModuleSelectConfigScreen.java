package net.ludocrypt.joisevis.modules.complex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleSelect;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleSelectConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleSelect moduleSelect = new ModuleSelect();

		moduleSelect.setControlSource(getManipulatingModule());
		moduleSelect.setLowSource(getManipulatingModule(1));
		moduleSelect.setHighSource(getManipulatingModule(2));
		moduleSelect.setThreshold(getManipulatingModule(3));
		moduleSelect.setFalloff(getManipulatingModule(4));

		return moduleSelect;
	}

	@Override
	public String getName() {
		return "Select";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleSelectConfigScreen();
	}

	@Override
	public int manipulatorCount() {
		return 5;
	}

	@Override
	public JPanel getManipulationPanel(int manipulator) {
		return new JPanel(new GridLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.setColor(Color.RED);
				for (int i = 0; i < 2; i++) {
					g.drawRect(i, i, getWidth() - 1 - 2 * i, getHeight() - 1 - 2 * i);
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

				Font font = new Font("Arial", Font.BOLD, 12);
				g.setFont(font);
				g.setColor(Color.BLACK);

				switch (manipulator) {
				case 0:
					g.drawString("Control", 3, 12);
					break;
				case 1:
					g.drawString("Low", 3, 12);
					break;
				case 2:
					g.drawString("High", 3, 12);
					break;
				case 3:
					g.drawString("Threshold", 3, 12);
					break;
				case 4:
					g.drawString("Falloff", 3, 12);
					break;
				}

			}
		};
	}

}
