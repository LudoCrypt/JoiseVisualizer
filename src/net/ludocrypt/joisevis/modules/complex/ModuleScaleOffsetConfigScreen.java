package net.ludocrypt.joisevis.modules.complex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleScaleOffset;

import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.GenericManipulatorScreen;

public class ModuleScaleOffsetConfigScreen extends GenericManipulatorScreen {

	@Override
	public void addOptions() {

	}

	@Override
	public Module getModule() {
		ModuleScaleOffset moduleScaleDomain = new ModuleScaleOffset();

		moduleScaleDomain.setSource(getManipulatingModule());
		moduleScaleDomain.setScale(getManipulatingModule(1));
		moduleScaleDomain.setOffset(getManipulatingModule(2));

		return moduleScaleDomain;
	}

	@Override
	public String getName() {
		return "Scale Offset";
	}

	@Override
	public GenericManipulatorScreen copy() {
		return new ModuleScaleOffsetConfigScreen();
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
					g.drawString("Source", 3, 12);
					break;
				case 1:
					g.drawString("Scale", 3, 12);
					break;
				case 2:
					g.drawString("Offset", 3, 12);
					break;
				}

			}
		};
	}

}
