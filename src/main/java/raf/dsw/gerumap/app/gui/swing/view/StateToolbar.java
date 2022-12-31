package raf.dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class StateToolbar extends JToolBar {
	public StateToolbar() {
		super(HORIZONTAL);
		setFloatable(false);
		add(MainFrame.getInstance().getActionManager().getSelectionStateAction());
		add(MainFrame.getInstance().getActionManager().getLinkStateAction());
		add(MainFrame.getInstance().getActionManager().getTermStateAction());
		add(MainFrame.getInstance().getActionManager().getDeleteStateAction());
		add(MainFrame.getInstance().getActionManager().getEditStateAction());
		add(MainFrame.getInstance().getActionManager().getMoveStateAction());
		add(MainFrame.getInstance().getActionManager().getZoomStateAction());
	}

}
