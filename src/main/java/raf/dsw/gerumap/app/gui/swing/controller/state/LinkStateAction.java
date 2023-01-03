package raf.dsw.gerumap.app.gui.swing.controller.state;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

public class LinkStateAction extends AbstractGerumapAction {

	public LinkStateAction() {
		putValue(NAME, "Link");
		putValue(SHORT_DESCRIPTION, "Link");
		putValue(SMALL_ICON, loadScaledIcon("/images/addLink.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectView) MainFrame.getInstance().getProjectView()).startLinkState();
	}
}
