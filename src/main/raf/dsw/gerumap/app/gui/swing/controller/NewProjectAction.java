package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGerumapAction {

	public NewProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		putValue(NAME, "New Project");
		putValue(SMALL_ICON, loadScaledIcon("/images/addNew.png"));
		putValue(SHORT_DESCRIPTION, "New Project");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
		MainFrame.getInstance().getMapTree().addChild(selected);
	}

}
