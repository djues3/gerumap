package raf.dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

	public Toolbar() {
		add(MainFrame.getInstance().getActionManager().getExitAction());
		add(MainFrame.getInstance().getActionManager().getNewProjectAction());
	}


}

