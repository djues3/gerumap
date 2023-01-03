package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import java.util.ArrayList;
import java.util.List;
import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class DeleteCommand extends AbstractCommand {

	MindMapView view;
	private List<Term> terms = new ArrayList<>();
	private List<Link> links = new ArrayList<>();

	public DeleteCommand(List<Term> terms, List<Link> links, MindMapView view) {
		if (terms != null) {
			this.terms = terms;
		}
		if (links != null) {
			this.links = links;
		}
		this.view = view;
	}

	@Override
	public void doCommand() {
		for (Term t : terms) {
//            view.getMindMap().removeChild(t);
			view.removePainter(view.getPainterForTerm(t));
		}
		for (Link l : links) {
//            view.getMindMap().removeChild(l);
			view.removePainter(view.getPainterForLink(l));
		}
	}

	@Override
	public void undoCommand() {
		for (Term t : terms) {
			if (t == null) {
				continue;
			}
			view.getMindMap().addChild(t);
//            view.removePainter(view.getPainterForTerm(t));
			view.addPainter(new TermPainter(t, view));
		}
		for (Link l : links) {
			if (l == null) {
				continue;
			}
			view.getMindMap().addChild(l);
//            view.removePainter(view.getPainterForLink(l));
			view.addPainter(new LinkPainter(l, view));
		}
	}
}
