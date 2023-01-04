package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.messageGenerator.Message.Level;

public class SaveTemplateAction extends AbstractGerumapAction {

	public SaveTemplateAction() {
		putValue(NAME, "Save template");
		putValue(SHORT_DESCRIPTION, "Save mind map as template");
		putValue(SMALL_ICON, loadScaledIcon("/images/template.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof MindMap map)) {
			AppCore.getInstance().getMessageGenerator().generate(
				"Only mind maps can be templates, choose the map you want to save in the tree",
				Level.WARNING);
			return;
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JavaScript Object Notation",
			"json");
		jfc.setDialogTitle("Save mind map as template");
		jfc.addChoosableFileFilter(filter);
		jfc.setCurrentDirectory(new File("src/main/resources/templates"));
		File mapfile;
		if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			mapfile = new File(jfc.getSelectedFile().toString() + ".json");
		} else {
			return;
		}

		AppCore.getInstance().getSerializer().saveMindMap(map, mapfile);

	}
}
