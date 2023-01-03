package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.controller.ActionManager;
import raf.dsw.gerumap.app.gui.swing.tree.MapTree;
import raf.dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import raf.dsw.gerumap.app.observer.IPublisher;
import raf.dsw.gerumap.app.observer.ISubscriber;


@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {

	private static MainFrame instance;
	private ActionManager actionManager;
	private JMenuBar menu;
	private JToolBar toolbar;
	private JPanel contentPanel;
	private Component projectView;
	private ProjectViewManager pvm;
	private JSplitPane splitPane;
	private MapTree mapTree = new MapTreeImplementation();

	private MainFrame() {
		pvm = ProjectViewManager.getInstance();
		pvm.addSubscriber(this);
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;

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

		projectView = pvm.getProjectView();
		JTree treeView = mapTree.generateTree(
			AppCore.getInstance().getMapRepository().getProjectExplorer());
		JScrollPane scroll = new JScrollPane(treeView);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, projectView);
		splitPane.setDividerLocation(175);
		contentPanel.add(splitPane);
		add(contentPanel, BorderLayout.CENTER);
	}


	@Override
	public void update(IPublisher publisher) {
		projectView = pvm.getProjectView();
		splitPane.setRightComponent(projectView);
	}
}
