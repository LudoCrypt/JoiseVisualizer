package net.ludocrypt.joisevis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ConfigHelper {

	public static void addCheckboxConfigOption(String optionName, String label, boolean defaultValue, JPanel panel, Map<String, Object> configValues) {
		JCheckBox checkBox = new JCheckBox(label);
		checkBox.setSelected((boolean) configValues.getOrDefault(optionName, defaultValue));
		checkBox.addItemListener(e -> {
			configValues.put(optionName, checkBox.isSelected());
			Main.currentMain.cachedModule = null;
			Main.currentMain.updateTexture();
		});

		panel.add(checkBox);
		configValues.put(optionName, configValues.getOrDefault(optionName, defaultValue));
	}

	public static JSpinner addConfigOption(String optionName, String label, Comparable minValue, Comparable maxValue, Number step, Number defaultValue, JPanel panel,
			Map<String, Object> configValues) {
		JLabel optionLabel = new JLabel(label);
		panel.add(optionLabel);

		JFormattedTextField optionField = new JFormattedTextField();
		optionField.setValue(configValues.getOrDefault(optionName, defaultValue));
		SpinnerNumberModel numberModel = new SpinnerNumberModel();
		numberModel.setMinimum(minValue);
		numberModel.setMaximum(maxValue);
		numberModel.setStepSize(step);
		numberModel.setValue(configValues.getOrDefault(optionName, defaultValue));
		JSpinner optionSpinner = new JSpinner(numberModel);
		optionSpinner.setPreferredSize(new Dimension(25, 25));
		optionField = ((JSpinner.DefaultEditor) optionSpinner.getEditor()).getTextField();
		optionField.setEditable(true);
		optionField.setHorizontalAlignment(JTextField.LEFT);
		panel.add(optionSpinner);

		configValues.put(optionName, numberModel.getValue());
		optionSpinner.addChangeListener(e -> {
			configValues.put(optionName, numberModel.getNumber());

			Main.currentMain.cachedModule = null;
			Main.currentMain.updateTexture();
		});

		optionField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		return optionSpinner;
	}

	public static <E extends Enum<E>> void addEnumConfigOption(String optionName, String label, E[] enumValues, E defaultValue, JPanel panel, Map<String, Object> configValues) {
		JLabel optionLabel = new JLabel(label);
		panel.add(optionLabel);

		JComboBox<E> comboBox = new JComboBox<>(enumValues);
		comboBox.setSelectedItem(configValues.getOrDefault(optionName, defaultValue));
		panel.add(comboBox);

		configValues.put(optionName, comboBox.getSelectedItem());
		comboBox.addActionListener(e -> {
			configValues.put(optionName, comboBox.getSelectedItem());
			Main.currentMain.cachedModule = null;
			Main.currentMain.updateTexture();
		});
	}

}
