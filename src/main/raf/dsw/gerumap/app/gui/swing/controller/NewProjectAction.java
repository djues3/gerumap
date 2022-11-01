package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.tree.MapTree;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class NewProjectAction extends AbstractGerumapAction {

	public NewProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(NAME, "New Project");
		putValue(SMALL_ICON, loadIcon("images/addNew.png"));
		putValue(SHORT_DESCRIPTION, "New Project");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
		MainFrame.getInstance().getMapTree().addChild(selected);
	}

}

