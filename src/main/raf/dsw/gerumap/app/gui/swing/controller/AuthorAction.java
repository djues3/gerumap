package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.gui.swing.view.AuthorDialog;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGerumapAction {

	public AuthorAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
		putValue(NAME, "Author");
		putValue(SMALL_ICON, loadScaledIcon("/images/author.png"));
		putValue(SHORT_DESCRIPTION, "Author");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if ((MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project project)) {
			AuthorDialog dialog = new AuthorDialog(project);
			dialog.setVisible(true);
		} else {
			System.err.println("Nije selektovan projekat");
		}
	}
}

