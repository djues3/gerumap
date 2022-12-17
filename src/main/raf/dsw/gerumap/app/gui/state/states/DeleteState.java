package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteState extends State {
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		x = (int)real.getX();
		y = (int)real.getY();
		MindMap map = view.getMindMap();
		Term termToRemove = null;
		List<Link> linksToRemove = new ArrayList<>();
		for(Painter p: view.getPainters()) {
			if(p instanceof TermPainter tp) {
				if(tp.elementAt(x, y)) {
					Term term = tp.getTerm();
					view.removePainter(tp);
					termToRemove = term;
					//						view.removePainter(view.getPainterForLink(l));
					//						l.getFrom().getLinks().remove(l);
					//						l.getTo().getLinks().remove(l);
					//						it.remove();
					linksToRemove.addAll(term.getLinks());
					break;
				}
			}
			if(p instanceof LinkPainter lp) {
				if(lp.elementAt(x, y)) {
					lp.getLink().getFrom().getLinks().remove(lp.getLink());
					lp.getLink().getTo().getLinks().remove(lp.getLink());
//					map.removeChild(lp.getLink());
					view.removePainter(lp);
					break;
				}
			}
		}
		if (termToRemove != null)
			view.removePainter(view.getPainterForTerm(termToRemove));
		for (Link l : linksToRemove) {
			map.removeChild(l);
			view.removePainter(view.getPainterForLink(l));
		}
		view.repaint();
	}
}
