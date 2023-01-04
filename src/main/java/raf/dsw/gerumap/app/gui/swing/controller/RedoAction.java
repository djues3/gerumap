package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.AppCore;

public class RedoAction extends AbstractGerumapAction {

	public RedoAction() {
		putValue(SMALL_ICON, loadScaledIcon("/images/redo.png"));
		putValue(NAME, "Redo");
		putValue(SHORT_DESCRIPTION, "Undo");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z,
			InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().getMapRepository().getCommandManager().redoCommand();
	}
}
