package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.Project;

public class SaveAsAction extends AbstractGerumapAction {

	public SaveAsAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
			KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadScaledIcon("/images/saveAs.png"));
		putValue(NAME, "Save action");
		putValue(SHORT_DESCRIPTION, "Save action");
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setSelectedFile(new File("."));
		jfc.setDialogTitle("Save as");
		if (!(MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof Project project)) {
			return;
		}
		File projectFile;
		if (!project.isModified()) {
			return;
		}
		if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			projectFile = new File(jfc.getSelectedFile().toString() + ".json");
			project.setFile(projectFile);
		} else {
			return;
		}
		AppCore.getInstance().getSerializer().saveProject(project);
		project.setModified(false);
	}
}
