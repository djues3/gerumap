package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.AppCore;

public class UndoAction extends AbstractGerumapAction {

	public UndoAction() {
		putValue(SMALL_ICON, loadScaledIcon("/images/undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().getMapRepository().getCommandManager().undoCommand();
	}
}
