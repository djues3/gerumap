package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class ExitAction extends AbstractGerumapAction {

	public ExitAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		putValue(SMALL_ICON, loadScaledIcon("/images/close.png"));
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
