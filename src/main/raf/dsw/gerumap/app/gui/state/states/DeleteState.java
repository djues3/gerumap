package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class DeleteState extends State {
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		MindMap map = view.getMindMap();
		for(Painter p: view.getPainters()) {
			if(p instanceof TermPainter tp) {
				if(tp.elementAt(x, y)) {
					Term term = tp.getTerm();
					for(Link l: term.getLinks()) {
						view.removePainter(view.getPainterForLink(l));
					}
					view.removePainter(tp);
					break;
				}
			}
			if(p instanceof LinkPainter lp) {
				if(lp.elementAt(x, y)) {
					lp.getLink().getFrom().getLinks().remove(lp.getLink());
					lp.getLink().getTo().getLinks().remove(lp.getLink());
					map.removeChild(lp.getLink());
					view.removePainter(lp);
					break;
				}
			}
		}
		view.repaint();
	}
}
