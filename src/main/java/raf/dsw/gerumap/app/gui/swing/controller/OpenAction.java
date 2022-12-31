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

public class OpenAction extends AbstractGerumapAction {

	public OpenAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
			KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadScaledIcon("/images/open.png"));
		putValue(NAME, "Open action");
		putValue(SHORT_DESCRIPTION, "Open action");
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".json", "json");
		jfc.addChoosableFileFilter(filter);
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = jfc.getSelectedFile();
				Project p = AppCore.getInstance().getSerializer().loadProject(file);
				MainFrame.getInstance().getMapTree().loadProject(p);

			} catch (Exception ex) {
				AppCore.getInstance().getMessageGenerator()
					.generate("Error while opening project", Message.Level.ERROR, ex);
			}
		}
	}
}