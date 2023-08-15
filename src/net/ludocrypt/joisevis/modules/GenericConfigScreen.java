package net.ludocrypt.joisevis.modules;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sudoplay.joise.module.Module;

import net.ludocrypt.joisevis.ConfigScrollPane;
import net.ludocrypt.joisevis.Main;
import net.ludocrypt.joisevis.MainConfig;

public abstract class GenericConfigScreen<T extends GenericConfigScreen<T>> {
	public JPanel controlPanel;
	public ConfigScrollPane scrollPane;
	public JFrame configFrame;
	public Map<String, Object> configValues = new HashMap<String, Object>();

	public void createUI() {
		configFrame = new JFrame(getName());
		configFrame.setDefaultCloseOperation(important() ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
		configFrame.setSize(width(), height());
		configFrame.setLocationRelativeTo(null);

		configFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				performActionOnComponents(configFrame, (component) -> Main.hoveringGrabComponents.remove(component));
				super.windowClosing(e);
			}

			void performActionOnComponents(Component parentComponent, Consumer<Component> action) {
				if (parentComponent instanceof Container) {
					Container container = (Container) parentComponent;
					Component[] components = container.getComponents();

					for (Component component : components) {
						action.accept(component); // Perform your custom action on the component here

						if (component instanceof Container) {
							performActionOnComponents(component, action); // Recursively call for child components
						}
					}
				}
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		addOptions();

		controlPanel.setPreferredSize(new Dimension(0, 40 * configValues.size()));

		scrollPane = new ConfigScrollPane(controlPanel, this);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(200, 40 * configValues.size()));

		if (this instanceof GenericManipulatorScreen manipulatorScreen) {
			controlPanel.setPreferredSize(new Dimension(controlPanel.getPreferredSize().width, 20 + controlPanel.getPreferredSize().height + 125 * manipulatorScreen.manipulatorCount()));
			scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 20 + scrollPane.getPreferredSize().height + 125 * manipulatorScreen.manipulatorCount()));
		}

		ComponentMouseListener component = new ComponentMouseListener();

		configFrame.addComponentListener(component);
		scrollPane.addMouseMotionListener(component);
		scrollPane.addMouseListener(component);
		configFrame.add(scrollPane);

		if (pack()) {
			configFrame.pack();
		}
		configFrame.setVisible(true);
	}

	class ComponentMouseListener implements ComponentListener, MouseMotionListener, MouseListener {

		@Override
		public void componentResized(ComponentEvent e) {
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			boolean caught = false;
			for (JComponent component : Main.hoveringGrabComponents) {
				if (!isComponentInParentTree(controlPanel, component) && !(GenericConfigScreen.this instanceof MainConfig && component != Main.currentMain.modulesPanel)) {
					if (inBounds(component) && component.getComponentCount() == 0) {
						configFrame.setVisible(false);

						component.add(scrollPane);
						repaintComponentAndParents(component);
						Main.currentMain.cachedModule = null;
						Main.currentMain.updateTexture();

						caught = true;
						break;
					}
				}
			}
			if (!caught) {
				for (JComponent component : Main.hoveringGrabComponents) {
					if (!isComponentInParentTree(controlPanel, component) && !(GenericConfigScreen.this instanceof MainConfig && component != Main.currentMain.modulesPanel)) {
						configFrame.setVisible(true);
						component.remove(scrollPane);
						repaintComponentAndParents(component);
						configFrame.add(scrollPane);
						Main.currentMain.cachedModule = null;
						Main.currentMain.updateTexture();
					}
				}
			}
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON2) {
				cloneScreen();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

			boolean caught = false;
			for (JComponent component : Main.hoveringGrabComponents) {
				if (!isComponentInParentTree(controlPanel, component) && !(GenericConfigScreen.this instanceof MainConfig && component != Main.currentMain.modulesPanel)) {
					if (inBounds(component) && component.getComponentCount() == 0) {
						configFrame.setVisible(false);

						component.add(scrollPane);
						repaintComponentAndParents(component);
						Main.currentMain.cachedModule = null;
						Main.currentMain.updateTexture();

						caught = true;
						break;
					}
				}
			}
			if (!caught) {
				for (JComponent component : Main.hoveringGrabComponents) {
					if (!isComponentInParentTree(controlPanel, component) && !(GenericConfigScreen.this instanceof MainConfig && component != Main.currentMain.modulesPanel)) {
						configFrame.setVisible(true);

						if (isComponentInParentTree(component, scrollPane)) {
							Main.currentMain.cachedModule = null;
							Main.currentMain.updateTexture();
							component.remove(scrollPane);
						}

						repaintComponentAndParents(component);
						configFrame.add(scrollPane);
						configFrame.setLocation(mouseLocation);
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		boolean inBounds(JComponent component) {
			return component.getMousePosition() != null;
		}

		static void repaintComponentAndParents(JComponent component) {
			if (component != null) {
				JComponent current = component;
				while (current != null) {
					current.revalidate();
					current.repaint();
					if (current.getParent() instanceof JComponent) {
						current = (JComponent) current.getParent();
					} else {
						break;
					}
				}
			}
		}

		static boolean isComponentInParentTree(JComponent parent, JComponent child) {
			Component current = child;

			while (current != null) {
				if (current == parent) {
					return true;
				}
				current = current.getParent();
			}

			return false;
		}

	}

	public abstract void addOptions();

	public abstract Module getModule();

	public abstract String getName();

	public T cloneScreen() {
		T copy = copy();

		if (copy != null) {
			copy.configValues.clear();
			copy.configValues.putAll(configValues);

			copy.createUI();
		}

		return copy;
	}

	public abstract T copy();

	public boolean important() {
		return false;
	}

	public boolean pack() {
		return true;
	}

	public int width() {
		return 400;
	}

	public int height() {
		return 600;
	}

	public long getLong(String config) {
		return (long) configValues.get(config);
	}

	public int getInt(String config) {
		return (int) configValues.get(config);
	}

	public double getDouble(String config) {
		return (double) configValues.get(config);
	}

	public float getFloat(String config) {
		return (float) configValues.get(config);
	}

	public boolean getBoolean(String config) {
		return (boolean) configValues.get(config);
	}
}
