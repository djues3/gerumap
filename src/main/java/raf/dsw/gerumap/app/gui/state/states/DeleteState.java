package raf.dsw.gerumap.app.gui.state.states;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.DeleteCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class DeleteState extends State {

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int) real.getX();
		y = (int) real.getY();
		Term termToRemove = null;
		List<Link> linksToRemove = new ArrayList<>();
		for (Painter p : view.getPainters()) {
			if (p instanceof TermPainter tp) {
				if (tp.elementAt(x, y)) {
					Term term = tp.getTerm();
					termToRemove = term;
					linksToRemove.addAll(term.getLinks());
					break;
				}
			}
			if (p instanceof LinkPainter lp) {
				if (lp.elementAt(x, y)) {

					linksToRemove.add(lp.getLink());

					break;
				}
			}
		}
		List<Term> termsToRemove = new ArrayList<>();
		termsToRemove.add(termToRemove);
		DeleteCommand command = new DeleteCommand(termsToRemove, linksToRemove, view);
		AppCore.getInstance().getMapRepository().getCommandManager().addCommand(command);
		view.repaint();
	}
}
