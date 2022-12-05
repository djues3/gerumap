package raf.dsw.gerumap.app.gui.state;

import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class TermState extends State {
	@Override
	public void mousePressed(int x, int y, MindMap map) {
		Term term = new Term();
		term.setX(x);
		term.setY(y);
		map.addChild(term);
	}
}
