package raf.dsw.gerumap.app.gui.state.states;

import java.awt.geom.Point2D;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.AddTermCommand;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class TermState extends State {

	@Override
	public void mouseClicked(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		if (checkIntersect((int) real.getX(), (int) real.getY(), view)) {
			return;
		}
		Term term = new Term();
		term.setX((int) real.getX());
		term.setY((int) real.getY());
		term.setText("New Term");
		AddTermCommand command = new AddTermCommand(view, term);
		MainFrame.getInstance().getCommandManager().addCommand(command);
		((Project) view.getMindMap().getParent()).setModified(true);
	}

	private boolean checkIntersect(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		for (Painter painter : view.getPainters()) {
			if (painter instanceof TermPainter tp) {
				if (tp.elementAt((int) real.getX(), (int) real.getY())) {
					return true;
				}
			}
		}
		return false;
	}
}
