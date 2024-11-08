package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class AddTermCommand extends AbstractCommand {

	private final MindMapView view;
	private final Term term;

	public AddTermCommand(MindMapView view, Term term) {
		this.view = view;
		this.term = term;
	}

	@Override
	public void doCommand() {
		view.getMindMap().addChild(term);
		term.setParent(view.getMindMap());
		TermPainter tp = new TermPainter(term, view);
		view.addPainter(tp);
	}

	@Override
	public void undoCommand() {
		view.getMindMap().removeChild(term);
		term.setParent(null);
		view.removePainter(view.getPainterForTerm(term));
	}
}
