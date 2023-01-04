package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.AppCore;

public class UndoAction extends AbstractGerumapAction {

	public UndoAction() {
		putValue(SMALL_ICON, loadScaledIcon("/images/undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().getMapRepository().getCommandManager().undoCommand();
	}
}
