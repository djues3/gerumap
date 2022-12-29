package raf.dsw.gerumap.app.gui.swing.controller.state;

import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.ActionEvent;

public class TermStateAction extends AbstractGerumapAction {
	public TermStateAction() {
		putValue(NAME, "Term");
		putValue(SHORT_DESCRIPTION, "Term");
		putValue(SMALL_ICON, loadScaledIcon("/images/term.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startTermState();
	}
}
