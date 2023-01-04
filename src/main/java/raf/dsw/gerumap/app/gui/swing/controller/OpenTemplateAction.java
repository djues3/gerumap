package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.tree.MapTree;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.Message.Level;

public class OpenTemplateAction extends AbstractGerumapAction {

	public OpenTemplateAction() {
		putValue(NAME, "Open template");
		putValue(SHORT_DESCRIPTION, "Open template mind map in current project");
		putValue(SMALL_ICON, loadScaledIcon("/images/template.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof Project)) {
			AppCore.getInstance().getMessageGenerator().generate(
				"Mind maps can only be added to projects, "
					+ "choose the project you want to add the map to in the tree",
				Level.WARNING);
			return;
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setName("Open");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"JavaScript Object Notation (.json)", "json");
		jfc.addChoosableFileFilter(filter);
		jfc.setCurrentDirectory(new File("src/main/resources/templates"));
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = jfc.getSelectedFile();
				MindMap m = AppCore.getInstance().getSerializer().loadMindMap(file);
				MapTree tree = MainFrame.getInstance().getMapTree();
				tree.loadMindMap(m);

			} catch (Exception ex) {
				AppCore.getInstance().getMessageGenerator()
					.generate("Error while opening mind map", Message.Level.ERROR, ex);
			}
		}
	}
}

