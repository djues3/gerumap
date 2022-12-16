package raf.dsw.gerumap.app.gui.swing.controller.state;

import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.ActionEvent;

public class SelectionStateAction extends AbstractGerumapAction {
	public SelectionStateAction() {
		putValue(NAME, "Select");
		putValue(SHORT_DESCRIPTION, "Select");
		putValue(SMALL_ICON, loadScaledIcon("/images/selectState.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startSelectionState();
	}
}
