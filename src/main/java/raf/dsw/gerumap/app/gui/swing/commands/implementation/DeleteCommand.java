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

	private final MindMapView view;
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
			t.setParent(null);
		}
		for (Link l : links) {
			l.getTo().getLinks().remove(l);
			l.getFrom().getLinks().remove(l);
//            view.getMindMap().removeChild(l);
			view.removePainter(view.getPainterForLink(l));
			l.setFrom(null);
			l.setTo(null);
			l.setParent(null);
		}
	}

	@Override
	public void undoCommand() {
		for (Term t : terms) {
			if (t == null) {
				continue;
			}
			view.getMindMap().addChild(t);
			view.addPainter(new TermPainter(t, view));
			t.setParent(view.getMindMap());
		}
		for (Link l : links) {
			if (l == null) {
				continue;
			}
			l.setParent(view.getMindMap());
			view.getMindMap().addChild(l);
			l.getTo().getLinks().add(l);
			l.getFrom().getLinks().add(l);
			view.addPainter(new LinkPainter(l, view));
			view.repaint();
		}
	}
}
