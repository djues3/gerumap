package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractGerumapAction {

	public NewAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		putValue(NAME, "New Project");
		putValue(SMALL_ICON, loadScaledIcon("/images/add.png"));
		putValue(SHORT_DESCRIPTION, "New Project");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
		if (selected == null) {
			AppCore.getInstance().getMessageGenerator().generate(MessageType.NODE_CANNOT_BE_ADDED, Message.Level.ERROR);
			return;
		}
		MainFrame.getInstance().getMapTree().addChild(selected);
	}

}

