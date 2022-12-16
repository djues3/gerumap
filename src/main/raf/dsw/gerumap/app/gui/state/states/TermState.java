package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class TermState extends State {
	private ZoomState zoomState;
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			view.getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		if(checkIntersect((int)real.getX(), (int)real.getY(), view))
			return;
		Term term = new Term();
		term.setX((int)real.getX());
		term.setY((int)real.getY());
		term.setText("New Term");
		view.getMindMap().addChild(term);
		view.addPainter(new TermPainter(term));
	}

	private boolean checkIntersect(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			view.getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		for(Painter painter : view.getPainters()) {
			if(painter instanceof TermPainter tp)
				if(tp.elementAt((int)real.getX(), (int)real.getY()))
					return true;
		}
		return false;
	}
}
