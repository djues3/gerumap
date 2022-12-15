package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;

public class TermState extends State {

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Component tmp= ((Component)MainFrame.getInstance().getPvm().getProjectView());
		ZoomState zoomState;
		if (tmp instanceof ProjectView)
			zoomState = ((ProjectView)tmp).getStateManager().getZoomState();
		else zoomState = null;
		if(checkIntersect(x, y, view))
			return;
		Term term = new Term();
		term.setX(x - zoomState.getOffsetX().intValue());
		term.setY(y - zoomState.getOffsetY().intValue());
		term.setText("New Term");
		view.getMindMap().addChild(term);
		view.addPainter(new TermPainter(term));
	}

	private boolean checkIntersect(int x, int y, MindMapView view) {
		for(Painter painter : view.getPainters()) {
			if(painter instanceof TermPainter tp)
				if(tp.elementAt(x, y))
					return true;
		}
		return false;
	}
}
