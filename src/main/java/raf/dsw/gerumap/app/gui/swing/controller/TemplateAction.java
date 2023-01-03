package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.messageGenerator.Message.Level;

public class TemplateAction extends AbstractGerumapAction {

	public TemplateAction() {
		putValue(NAME, "Template");
		putValue(SHORT_DESCRIPTION, "Template");
		putValue(SMALL_ICON, loadScaledIcon("/images/template.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof MindMap map)) {
			AppCore.getInstance().getMessageGenerator().generate(
				"Only mind maps can be templates", Level.WARNING);
			return;
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("src/main/resources/templates"));
		File mapfile;
		if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			mapfile = new File(jfc.getSelectedFile().toString() + ".json");
		} else {
			return;
		}

		AppCore.getInstance().getSerializer()
			.saveMindMap(map, mapfile);

	}
}
