package net.ludocrypt.joisevis;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.ludocrypt.joisevis.modules.CellularModuleConfigScreen;
import net.ludocrypt.joisevis.modules.FractalModuleConfigScreen;
import net.ludocrypt.joisevis.modules.GenericConfigScreen;
import net.ludocrypt.joisevis.modules.ModuleBasisConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleAutoCorrectConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleBiasConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleBlendConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleBrightContrastConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleCombinerConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleFunctionGradientConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleGainConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleMagnitudeConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleNormalizedCoordsConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleRotateDomainConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleSawtoothConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleScaleDomainConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleScaleOffsetConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleSelectConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleTiersConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleTranslateDomainConfigScreen;
import net.ludocrypt.joisevis.modules.complex.ModuleTriangleConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleAbsConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleClampConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleConstConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleCosConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleInvertConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModulePowConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleReciprocalConfigScreen;
import net.ludocrypt.joisevis.modules.simple.ModuleSinConfigScreen;

public class ModulesScreen {

	JFrame buttonFrame;

	public ModulesScreen() {
		buttonFrame = new JFrame();
		buttonFrame.setTitle("Add Modules");
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonFrame.setSize(300, 600);
		buttonFrame.setLocationRelativeTo(null);

		JPanel simpleModulesPanel = new JPanel();
		simpleModulesPanel.setLayout(new GridLayout(0, 1));

		JPanel mathModulesPanel = new JPanel();
		mathModulesPanel.setLayout(new GridLayout(0, 1));

		JPanel otherModulesPanel = new JPanel();
		otherModulesPanel.setLayout(new GridLayout(0, 1));

		JScrollPane simpleModules = new JScrollPane(simpleModulesPanel);
		simpleModules.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane mathModules = new JScrollPane(mathModulesPanel);
		mathModules.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane otherModules = new JScrollPane(otherModulesPanel);
		otherModules.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		buttonFrame.setLayout(new GridLayout(3, 1));
		buttonFrame.add(simpleModules);
		buttonFrame.add(mathModules);
		buttonFrame.add(otherModules);

		createButton("Constant", ModuleConstConfigScreen::new, mathModulesPanel);
		createButton("Reciprocal", ModuleReciprocalConfigScreen::new, mathModulesPanel);
		createButton("Absolute Value", ModuleAbsConfigScreen::new, mathModulesPanel);
		createButton("Clamp", ModuleClampConfigScreen::new, mathModulesPanel);
		createButton("Sin", ModuleSinConfigScreen::new, mathModulesPanel);
		createButton("Cos", ModuleCosConfigScreen::new, mathModulesPanel);
		createButton("Invert", ModuleInvertConfigScreen::new, mathModulesPanel);
		createButton("Power", ModulePowConfigScreen::new, mathModulesPanel);

		createButton("Combiner", ModuleCombinerConfigScreen::new, otherModulesPanel);
		createButton("Auto Correct", ModuleAutoCorrectConfigScreen::new, otherModulesPanel);

		createButton("Translate Domain", ModuleTranslateDomainConfigScreen::new, otherModulesPanel);
		createButton("Scale Domain", ModuleScaleDomainConfigScreen::new, otherModulesPanel);
		createButton("Rotate Domain", ModuleRotateDomainConfigScreen::new, otherModulesPanel);
		createButton("Scale Offset", ModuleScaleOffsetConfigScreen::new, otherModulesPanel);
		createButton("Magnitude", ModuleMagnitudeConfigScreen::new, otherModulesPanel);
		createButton("Sawtooth", ModuleSawtoothConfigScreen::new, otherModulesPanel);
		createButton("Triangle", ModuleTriangleConfigScreen::new, otherModulesPanel);
		createButton("Gain", ModuleGainConfigScreen::new, otherModulesPanel);
		createButton("Bias", ModuleBiasConfigScreen::new, otherModulesPanel);
		createButton("Blend", ModuleBlendConfigScreen::new, otherModulesPanel);
		createButton("Brightness and Contrast", ModuleBrightContrastConfigScreen::new, otherModulesPanel);
		createButton("Function Gradient", ModuleFunctionGradientConfigScreen::new, otherModulesPanel);
		createButton("Tiers", ModuleTiersConfigScreen::new, otherModulesPanel);
		createButton("Normalized Coordinates", ModuleNormalizedCoordsConfigScreen::new, otherModulesPanel);
		createButton("Select", ModuleSelectConfigScreen::new, otherModulesPanel);

		createButton("Basis", ModuleBasisConfigScreen::new, simpleModulesPanel);
		createButton("Fractal", FractalModuleConfigScreen::new, simpleModulesPanel);
		createButton("Cellular", CellularModuleConfigScreen::new, simpleModulesPanel);

		buttonFrame.setVisible(true);
	}

	void createButton(String name, Supplier<GenericConfigScreen> screen, JPanel to) {
		JButton module = new JButton(name);

		module.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.get().createUI();
			}
		});

		to.add(module);
	}

}
