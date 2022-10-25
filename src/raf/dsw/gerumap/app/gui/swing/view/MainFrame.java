package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.controller.ActionManager;
import javax.swing.*;
import java.awt.*;


@Getter
@Setter
public class MainFrame extends JFrame {
	private static MainFrame instance;
	private ActionManager actionManager;
	private JMenuBar menu;
	private JToolBar toolbar;
	private JPanel contentPanel;


	private MainFrame() {
	}


	public void init() {
		actionManager = new ActionManager();
		initGUI();
		setVisible(true);
	}


	private void initGUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(screenSize.width / 2, screenSize.height / 2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gerumap");
		menu = new MenuBar();
		setJMenuBar(menu);
		toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		// Za sada su ove dve komponente placeholderi, ali ce kasnije biti promenjene na  klase
		ProjectView projectView = new ProjectView();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(), projectView);
		splitPane.setDividerLocation(175);
		contentPanel.add(splitPane);
		add(contentPanel, BorderLayout.CENTER);

	}


	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;

	}
}
