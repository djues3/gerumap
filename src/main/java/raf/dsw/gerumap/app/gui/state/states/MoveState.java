package raf.dsw.gerumap.app.gui.state.states;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.MoveCommand;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.Element;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

@Getter
@Setter
public class MoveState extends State {

	List<TermPainter> toMove;
	private int startX;
	private int startY;
	private int startXReal, startYReal, endXReal, endYReal;
	private boolean viewMove = false;

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		startX = x;
		startY = y;
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		startXReal = (int) real.getX();
		startYReal = (int) real.getY();
		Term term = view.getMindMap().getTermAt(x, y);
		toMove = new ArrayList<>();
		if (term == null) {
			return;
		}
		ProjectView pv = (ProjectView) MainFrame.getInstance().getProjectView();

		boolean flag = false;
		for (TermPainter tp : pv.getStateManager().getSelectedTerms()) {
			if (tp.getTerm().equals(term)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			toMove = pv.getStateManager().getSelectedTerms();
		} else {
			TermPainter tp = view.getPainterForTerm(term);
			toMove.add(tp);
		}
	}

	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		if (toMove.isEmpty()) {
			return;
		}
		for (TermPainter tp : toMove) {
			tp.getTerm().setX(tp.getTerm().getX() + x - startX);
			tp.getTerm().setY(tp.getTerm().getY() + y - startY);
			tp.getTerm().publish();
		}
		view.repaint();
		startX = x;
		startY = y;
	}

	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		endXReal = (int) real.getX();
		endYReal = (int) real.getY();

		ArrayList<Element> elements = new ArrayList<>();
		for (TermPainter tp : toMove) {
			elements.add(tp.getTerm());
			tp.getTerm().setX(tp.getTerm().getX() - (endXReal - startXReal));
			tp.getTerm().setY(tp.getTerm().getY() - (endYReal - startYReal));
		}
		MoveCommand command = new MoveCommand(elements, endXReal - startXReal,
			endYReal - startYReal, view);
		MainFrame.getInstance().getCommandManager().addCommand(command);
		view.repaint();
	}
}
