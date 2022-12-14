package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class TermState extends State {
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		if(checkIntersect(x, y, view))
			return;
		Term term = new Term();
		term.setX(x);
		term.setY(y);
		term.setText("New Term");
		view.getMindMap().addChild(term);
		view.addPainter(new TermPainter(term));
	}

	private boolean checkIntersect(int x, int y, MindMapView view) {
		for(Painter painter : view.getPainters()) {
			if(painter instanceof TermPainter tp)
				if(tp.elementAt(x, y))
					return true;
		}
		return false;
	}
}
