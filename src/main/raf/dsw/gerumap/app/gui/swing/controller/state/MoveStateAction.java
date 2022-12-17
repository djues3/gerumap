package raf.dsw.gerumap.app.gui.swing.controller.state;

import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.ActionEvent;

public class MoveStateAction extends AbstractGerumapAction {
	public MoveStateAction() {
		putValue(NAME, "Move");
		putValue(SHORT_DESCRIPTION, "Move");
		putValue(SMALL_ICON, loadScaledIcon("/images/moveState.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startMoveState();
	}
}
