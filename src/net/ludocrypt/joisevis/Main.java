package net.ludocrypt.joisevis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.sudoplay.joise.module.Module;

public class Main {
	public static Main currentMain;
	public static ArrayList<JComponent> hoveringGrabComponents = new ArrayList<JComponent>();
	public JFrame imageFrame;
	public JPanel imagePanel;
	public JLabel textureLabel;

	public JFrame modulesFrame;
	public JPanel modulesPanel;

	public int imgOffsX = 0;
	public int imgOffsY = 0;

	public int relativeFrequency = 1;

	public MainConfig mainConfig;
	public ModulesScreen modulesScreen;

	public Module cachedModule;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Main::new);
	}

	public Main() {
		currentMain = this;

		mainConfig = new MainConfig();
		mainConfig.createUI();
		mainConfig.configFrame.setLocation(mainConfig.configFrame.getLocation().x + 436, mainConfig.configFrame.getLocation().y + 196);

		modulesScreen = new ModulesScreen();
		modulesScreen.buttonFrame.setLocation(modulesScreen.buttonFrame.getLocation().x - 436, modulesScreen.buttonFrame.getLocation().y);

		createModules();
		createImageUI();
		updateTexture();
	}

	private void createModules() {
		modulesFrame = new JFrame("Module List");
		modulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modulesFrame.setSize(300, 400);

		modulesPanel = new JPanel();
		modulesPanel.setLayout(new BoxLayout(modulesPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(modulesPanel);
		modulesFrame.add(scrollPane);

		hoveringGrabComponents.add(modulesPanel);

		modulesFrame.setLocationRelativeTo(null);
		modulesFrame.setLocation(modulesFrame.getLocation().x + 436, modulesFrame.getLocation().y - 100);

		modulesFrame.setVisible(true);
	}

	private void createImageUI() {
		imageFrame = new JFrame("Procedural Texture Generator");
		imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageFrame.setSize(600, 600);

		imageFrame.setLocationRelativeTo(null);

		imagePanel = new JPanel();
		textureLabel = new JLabel();
		imagePanel = new JPanel(new BorderLayout()); // Use BorderLayout

		JPanel centeringPanel = new JPanel(new GridBagLayout()); // Panel for centering
		textureLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the label's content
		centeringPanel.add(textureLabel);

		imagePanel.add(centeringPanel, BorderLayout.CENTER); // Add centeringPanel to imagePanel

		imageFrame.add(imagePanel);

		JLabel mousePositionLabel = new JLabel("Position: (0, 0): 0.0");
		mousePositionLabel.setForeground(Color.RED);
		mousePositionLabel.setVisible(true);

		JPanel glassPane = new JPanel();
		glassPane.setOpaque(false);
		imageFrame.setGlassPane(glassPane);
		glassPane.setLayout(new BorderLayout());

		mousePositionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		glassPane.add(mousePositionLabel, BorderLayout.SOUTH);

		imageFrame.getGlassPane().setVisible(true);

		MouseAdapter adapter = new MouseAdapter() {

			int lastPressX = 0;
			int lastPressY = 0;

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				lastPressX = e.getX();
				lastPressY = e.getY();
				updateTexture();

				if (e.getClickCount() == 2) {
					JFrame positionFrame = new JFrame("Goto");
					positionFrame.setSize(400, 200);
					positionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					positionFrame.setVisible(true);
					positionFrame.setLocation(e.getXOnScreen() - (positionFrame.getWidth() / 2), e.getYOnScreen() - (positionFrame.getHeight() / 2));
					JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

					JLabel optionXLabel = new JLabel("Position X");
					panel.add(optionXLabel);

					JFormattedTextField optionXField = new JFormattedTextField();
					optionXField.setValue(e.getX() + imgOffsX);
					SpinnerNumberModel numberXModel = new SpinnerNumberModel();
					numberXModel.setMinimum(Integer.MIN_VALUE);
					numberXModel.setMaximum(Integer.MAX_VALUE);
					numberXModel.setStepSize(1);
					numberXModel.setValue(e.getX() + imgOffsX);
					JSpinner optionXSpinner = new JSpinner(numberXModel);
					optionXField = ((JSpinner.DefaultEditor) optionXSpinner.getEditor()).getTextField();
					optionXField.setEditable(true);
					optionXField.setHorizontalAlignment(JTextField.LEFT);
					panel.add(optionXSpinner);

					JLabel optionYLabel = new JLabel("Position Y");
					panel.add(optionYLabel);

					JFormattedTextField optionYField = new JFormattedTextField();
					optionYField.setValue(e.getY() + imgOffsY);
					SpinnerNumberModel numberYModel = new SpinnerNumberModel();
					numberYModel.setMinimum(Integer.MIN_VALUE);
					numberYModel.setMaximum(Integer.MAX_VALUE);
					numberYModel.setStepSize(1);
					numberYModel.setValue(e.getY() + imgOffsY);
					JSpinner optionYSpinner = new JSpinner(numberYModel);
					optionYField = ((JSpinner.DefaultEditor) optionYSpinner.getEditor()).getTextField();
					optionYField.setEditable(true);
					optionYField.setHorizontalAlignment(JTextField.LEFT);
					panel.add(optionYSpinner);

					ActionListener actionListener = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							imgOffsX = (int) optionXSpinner.getValue() - (int) mainConfig.configValues.get("width") / 2;
							imgOffsY = (int) optionYSpinner.getValue() - (int) mainConfig.configValues.get("height") / 2;
							updateTexture();
							positionFrame.dispose();
						}
					};

					optionYField.addActionListener(actionListener);
					optionXField.addActionListener(actionListener);
					JButton button = new JButton("Goto");
					button.addActionListener(actionListener);
					panel.add(button);

					positionFrame.add(panel);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				imgOffsX -= Math.floorDiv(e.getX() - lastPressX, relativeFrequency);
				imgOffsY -= Math.floorDiv(e.getY() - lastPressY, relativeFrequency);
				lastPressX = 0;
				lastPressY = 0;
				updateTexture();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				mousePositionLabel.setText("Position: (" + ((int) Math.floorDiv(e.getX(), relativeFrequency) + imgOffsX) + ", " + ((int) Math.floorDiv(e.getY(), relativeFrequency) + imgOffsY) + "): "
						+ getAt(e.getX(), e.getY()));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				updateTexture(Math.floorDiv(e.getX() - lastPressX, relativeFrequency), Math.floorDiv(e.getY() - lastPressY, relativeFrequency));
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				super.mouseWheelMoved(e);

				imgOffsX += Math.floorDiv(e.getX(), relativeFrequency);
				imgOffsY += Math.floorDiv(e.getY(), relativeFrequency);

				relativeFrequency -= e.getWheelRotation();
				relativeFrequency = Math.max(relativeFrequency, 1);

				imgOffsX -= Math.floorDiv(e.getX(), relativeFrequency);
				imgOffsY -= Math.floorDiv(e.getY(), relativeFrequency);

				updateTexture();
			}
		};

		// Add mouse motion listener to update the label's text
		textureLabel.addMouseMotionListener(adapter);
		textureLabel.addMouseListener(adapter);
		textureLabel.addMouseWheelListener(adapter);

		imageFrame.setVisible(true);
	}

	public void updateTexture() {
		updateTexture(0, 0);
	}

	void updateTexture(int xoff, int yoff) {
		BufferedImage textureImage = generateProceduralTexture(xoff, yoff);
		ImageIcon imageIcon = new ImageIcon(textureImage);
		textureLabel.setIcon(imageIcon);
	}

	private BufferedImage generateProceduralTexture(int xoff, int yoff) {
		int width = (int) mainConfig.configValues.get("width");
		int height = (int) mainConfig.configValues.get("height");

		BufferedImage textureImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int[] colors = new int[width * height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				colors[y * width + x] = getColorFromStrength(getModule().get(Math.floorDiv(x, relativeFrequency) + imgOffsX - xoff, 0, Math.floorDiv(y, relativeFrequency) + imgOffsY - yoff));
			}
		}

		textureImage.setRGB(0, 0, width, height, colors, 0, width);

		return textureImage;
	}

	private double getAt(int x, int y) {
		return getModule().get(Math.floorDiv(x, relativeFrequency) + imgOffsX, 0, Math.floorDiv(y, relativeFrequency) + imgOffsY);
	}

	private Module getModule() {
		if (cachedModule == null) {
			return cachedModule = createModule();
		}
		return cachedModule;
	}

	private Module createModule() {
		Module baseModule = mainConfig.getModule();

		for (Component component : modulesPanel.getComponents()) {
			if (component instanceof ConfigScrollPane configScrollPane) {
				return configScrollPane.configScreen.getModule();
			}
		}

		return baseModule;
	}

	private int getColorFromStrength(double strength) {
		if (strength >= 0 && strength <= 1) {
			// Grayscale mapping
			int grayscaleValue = (int) (strength * 255);
			return (grayscaleValue << 16) | (grayscaleValue << 8) | grayscaleValue;
		} else if (strength > 1 && strength <= 2) {
			// Green mapping
			int greenValue = (int) (((-strength) + 2) * 255);
			return (0 << 16) | (greenValue << 8) | 0;
		} else if (strength < 0 && strength >= -1) {
			// Red mapping
			int redValue = (int) (-strength * 255);
			return (redValue << 16) | (0 << 8) | 0;
		} else if (strength > 2) {
			// Blue mapping for values above 2
			return 255;
		} else {
			// Blue mapping for values below -1
			return 255 << 16;
		}
	}

}
