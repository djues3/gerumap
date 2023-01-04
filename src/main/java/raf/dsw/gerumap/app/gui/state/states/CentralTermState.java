package raf.dsw.gerumap.app.gui.state.states;

import java.awt.geom.Point2D;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class CentralTermState extends State {

	@Override
	public void mouseClicked(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int) real.getX();
		y = (int) real.getY();
		for (MapNode node : view.getMindMap().getChildren()) {
			if (node instanceof Term term) {
				term.setCentralTerm(false);
			}
		}
 		Term term = view.getMindMap().getTermAt(x, y);
		if (term == null) {
			return;
		}
		x = view.getWidth() / 2;
		y = view.getHeight() / 2;
		term.setX(x);
		term.setY(y);
		term.setCentralTerm(true);
	}
}
