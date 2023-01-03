package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class RemoveTermCommand extends AbstractCommand {
    MindMapView view;
    Term term;

    public RemoveTermCommand(MindMapView view, Term term) {
        this.view = view;
        this.term = term;
    }

    @Override
    public void doCommand() {
        view.getMindMap().removeChild(term);
        view.removePainter(view.getPainterForTerm(term));

    }

    @Override
    public void undoCommand() {
        view.getMindMap().addChild(term);
        TermPainter tp = new TermPainter(term, view);
        view.addPainter(tp);
    }
}
