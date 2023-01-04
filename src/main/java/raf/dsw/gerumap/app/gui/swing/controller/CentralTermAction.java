package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

public class CentralTermAction extends AbstractGerumapAction {

	public CentralTermAction() {
		putValue(NAME, "Central Term");
		putValue(SHORT_DESCRIPTION, "Central Term");
		putValue(SMALL_ICON, loadScaledIcon("/images/centralTerm.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startCentralTermState();
	}
}
