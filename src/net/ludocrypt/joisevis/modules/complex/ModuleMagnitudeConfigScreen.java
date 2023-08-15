package net.ludocrypt.joisevis.modules.complex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleMagnitude;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleMagnitudeConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleMagnitude moduleMagnitude = new ModuleMagnitude();

		moduleMagnitude.setX(getManipulatingModule(0));
		moduleMagnitude.setY(getManipulatingModule(1));
		moduleMagnitude.setZ(getManipulatingModule(2));

		return moduleMagnitude;
	}

	@Override
	public String getName() {
		return "Magnitude";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleMagnitudeConfigScreen();
	}

	@Override
	public int manipulatorCount() {
		return 3;
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
					g.drawString("X", 3, 12);
					break;
				case 1:
					g.drawString("Y", 3, 12);
					break;
				case 2:
					g.drawString("Z", 3, 12);
					break;
				}

			}
		};
	}

}
