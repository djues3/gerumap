package raf.dsw.gerumap.app.gui.swing.controller.state;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

public class MoveStateAction extends AbstractGerumapAction {

	public MoveStateAction() {
		putValue(NAME, "Move");
		putValue(SHORT_DESCRIPTION, "Move");
		putValue(SMALL_ICON, loadScaledIcon("/images/move.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startMoveState();
	}
}
