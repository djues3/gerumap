package raf.dsw.gerumap.app.gui.swing.controller.state;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

public class EditStateAction extends AbstractGerumapAction {

	public EditStateAction() {
		putValue(NAME, "Edit");
		putValue(SHORT_DESCRIPTION, "Edit");
		putValue(SMALL_ICON, loadScaledIcon("/images/editState.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startEditState();
	}
}
