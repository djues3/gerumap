package raf.dsw.gerumap.app.gui.swing.view;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	public Toolbar() {
		super(HORIZONTAL);
		setFloatable(false);

//		add(MainFrame.getInstance().getActionManager().getExitAction());
		add(MainFrame.getInstance().getActionManager().getNewAction());
		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		add(MainFrame.getInstance().getActionManager().getOpenAction());
		add(MainFrame.getInstance().getActionManager().getSaveAction());
		add(MainFrame.getInstance().getActionManager().getUndoAction());
		add(MainFrame.getInstance().getActionManager().getRedoAction());
		add(MainFrame.getInstance().getActionManager().getSaveAsAction());
		add(MainFrame.getInstance().getActionManager().getInfoAction());
		add(MainFrame.getInstance().getActionManager().getAuthorAction());
		add(MainFrame.getInstance().getActionManager().getExportAction());
		add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
		add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
	}


}

