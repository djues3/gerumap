package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class CentralTermAction extends AbstractGerumapAction {
	public CentralTermAction() {
		putValue(NAME, "Central Term");
		putValue(SHORT_DESCRIPTION, "Central Term");
		putValue(SMALL_ICON, loadScaledIcon("/images/centralTerm.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e.getSource() instanceof Term t)) {
			return;
		}
		t.setCentralTerm(!t.isCentralTerm());
		t.publish();
	}
}
