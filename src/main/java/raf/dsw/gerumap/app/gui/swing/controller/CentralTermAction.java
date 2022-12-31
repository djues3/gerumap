package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class CentralTermAction extends AbstractGerumapAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e.getSource() instanceof Term t)) {
			return;
		}
		t.setCentralTerm(!t.isCentralTerm());
		t.publish();
	}
}
