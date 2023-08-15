package net.ludocrypt.joisevis.modules;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleBasisFunction;
import com.sudoplay.joise.module.ModuleFractal;

import net.ludocrypt.joisevis.ConfigHelper;

public class FractalModuleConfigScreen extends GenericConfigScreen {

	@Override
	public void addOptions() {
		ConfigHelper.addConfigOption("seed", "Seed:", Long.MIN_VALUE, Long.MAX_VALUE, 1L, 1337L, controlPanel, configValues);
		ConfigHelper.addConfigOption("frequency", "Frequency:", 1, Integer.MAX_VALUE, 5, 300, controlPanel, configValues);
		ConfigHelper.addEnumConfigOption("moduleFractalType", "Fractal Type:", ModuleFractal.FractalType.values(), ModuleFractal.FractalType.FBM, controlPanel, configValues);

		JPanel octaveBasisPanel = new JPanel();
		octaveBasisPanel.setLayout(new BoxLayout(octaveBasisPanel, BoxLayout.Y_AXIS));

		JScrollPane octavesBasisScrollPane = new JScrollPane(octaveBasisPanel);
		octavesBasisScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel octaveInterpolationPanel = new JPanel();
		octaveInterpolationPanel.setLayout(new BoxLayout(octaveInterpolationPanel, BoxLayout.Y_AXIS));

		JScrollPane octavesInterpolationScrollPane = new JScrollPane(octaveInterpolationPanel);
		octavesInterpolationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JSpinner octavesConfig = ConfigHelper.addConfigOption("octaves", "Octaves:", 1, 10, 1, 5, controlPanel, configValues);
		octavesConfig.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				octaveBasisPanel.removeAll();
				octaveInterpolationPanel.removeAll();
				for (int i = 0; i < (int) octavesConfig.getValue(); i++) {
					ConfigHelper.addEnumConfigOption("octaveBasis" + i, "Octave #" + i + " Basis", ModuleBasisFunction.BasisType.values(), ModuleBasisFunction.BasisType.SIMPLEX, octaveBasisPanel,
							configValues);
					ConfigHelper.addEnumConfigOption("octaveInterpolation" + i, "Octave #" + i + " Interpolation", ModuleBasisFunction.InterpolationType.values(),
							ModuleBasisFunction.InterpolationType.CUBIC, octaveInterpolationPanel, configValues);
				}
				octaveBasisPanel.revalidate();
				octaveBasisPanel.repaint();
				octaveInterpolationPanel.revalidate();
				octaveInterpolationPanel.repaint();
			}

		});

		octavesConfig.getChangeListeners()[0].stateChanged(new ChangeEvent(octavesConfig));
		controlPanel.add(octavesBasisScrollPane);
		controlPanel.add(octavesInterpolationScrollPane);
	}

	@Override
	public Module getModule() {
		ModuleFractal gen = new ModuleFractal();
		gen.setNumOctaves(getInt("octaves"));
		gen.setFrequency(1.0D / (double) getInt("frequency"));
		gen.setType((ModuleFractal.FractalType) configValues.get("moduleFractalType"));
		gen.setSeed((long) configValues.get("seed"));

		for (int i = 0; i < getInt("octaves"); i++) {
			gen.setSourceType(i, (ModuleBasisFunction.BasisType) configValues.get("octaveBasis" + i), (ModuleBasisFunction.InterpolationType) configValues.get("octaveInterpolation" + i));
		}

		return gen;
	}

	@Override
	public String getName() {
		return "Fractal";
	}

	@Override
	public GenericConfigScreen copy() {
		return new FractalModuleConfigScreen();
	}

}
