package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.AppCore;

public class RedoAction extends AbstractGerumapAction {

	public RedoAction() {
		putValue(SMALL_ICON, loadScaledIcon("/images/redo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().getMapRepository().getCommandManager().redoCommand();
	}
}
