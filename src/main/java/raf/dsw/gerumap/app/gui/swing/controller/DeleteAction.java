package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;

public class DeleteAction extends AbstractGerumapAction {

	public DeleteAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(ACCELERATOR_KEY,
			KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));
		putValue(NAME, "Delete");
		putValue(SMALL_ICON, loadScaledIcon("/images/delete.png"));
		putValue(SHORT_DESCRIPTION, "Delete");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
		MainFrame.getInstance().getMapTree().remove(selected);
	}
}
