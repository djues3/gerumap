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
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.messageGenerator.Message;

public class SaveAction extends AbstractGerumapAction {


	public SaveAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
			KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadScaledIcon("/images/save.png"));
		putValue(NAME, "Save action");
		putValue(SHORT_DESCRIPTION, "Save action");
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JavaScript Object Notation",
			"json");
		jfc.setFileFilter(filter);
		jfc.setDialogTitle("Save");
		if (!(MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof Project project)) {
			AppCore.getInstance().getMessageGenerator().generate(
				"Only projects can be saved", Message.Level.ERROR);
			return;
		}

		File projectFile;

		if (!project.isModified()) {
			return;
		}

		if (project.getFile() == null || project.getFile().toString().isEmpty()) {
			if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
				projectFile = new File(jfc.getSelectedFile().toString() + ".json");
				project.setFile(projectFile);
			} else {
				return;
			}

		}

		AppCore.getInstance().getSerializer()
			.saveProject(project);

		project.setModified(false);
	}
}
