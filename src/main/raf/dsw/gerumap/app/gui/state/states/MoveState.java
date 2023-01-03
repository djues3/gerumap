package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.AddTermCommand;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.MoveCommand;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;

import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.Element;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MoveState extends State {
	private int startX;
	private int startY;
	private int startXReal, startYReal, endXReal, endYReal;
	List<TermPainter> toMove;

	private boolean viewMove = false;
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			((ProjectView)MainFrame.getInstance().getProjectView()).getMindMapView().getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		x = (int)real.getX();
		y = (int)real.getY();
		startXReal = x;
		startYReal = y;
		Term term = view.getMindMap().getTermAt(x, y);
		toMove = new ArrayList<>();
		if(term == null)
			return;
		ProjectView pv = (ProjectView) MainFrame.getInstance().getProjectView();
		startX = (int)screen.getX();
		startY = (int)screen.getY();

		boolean flag = false;
		for(TermPainter tp: pv.getStateManager().getSelectedTerms()) {
			if(tp.getTerm().equals(term)) {
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
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			((ProjectView)MainFrame.getInstance().getProjectView()).getMindMapView().getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		if(toMove.isEmpty())
			return;
		for (TermPainter tp : toMove) {
			tp.getTerm().setX(tp.getTerm().getX() + (int)screen.getX() - startX);
			tp.getTerm().setY(tp.getTerm().getY() + (int)screen.getY() - startY);
			tp.getTerm().publish();
		}
		view.repaint();
		startX = (int)screen.getX();
		startY = (int)screen.getY();
	}

	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			((ProjectView)MainFrame.getInstance().getProjectView()).getMindMapView().getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		endXReal = (int)real.getX();
		endYReal = (int)real.getY();

		ArrayList<Element> elements = new ArrayList<>();
		for (TermPainter tp : toMove) {
			elements.add((Element) tp.getTerm());
			tp.getTerm().setX(tp.getTerm().getX() - (endXReal - startXReal));
			tp.getTerm().setY(tp.getTerm().getY() - (endYReal - startYReal));
		}
		MoveCommand command = new MoveCommand(elements, endXReal - startXReal, endYReal - startYReal, view);
		AppCore.getInstance().getMapRepository().getCommandManager().addCommand(command);
		view.repaint();
	}
}