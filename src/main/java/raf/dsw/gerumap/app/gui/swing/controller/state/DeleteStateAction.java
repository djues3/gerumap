package raf.dsw.gerumap.app.gui.swing.controller.state;

import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.ActionEvent;

public class DeleteStateAction extends AbstractGerumapAction {

	public DeleteStateAction() {
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
		putValue(SMALL_ICON, loadScaledIcon("/images/deleteState.png"));
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ProjectView pv = (ProjectView) MainFrame.getInstance().getProjectView();
		pv.startDeleteState();
	}
}
