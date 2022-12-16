package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditState extends State {
	public boolean tryToEdit() {
		ProjectView pv = (ProjectView) MainFrame.getInstance().getProjectView();
		List<TermPainter> terms = pv.getStateManager().getSelectedTerms();
		if(terms == null)
			return false;
		if(!terms.isEmpty()) {
			Color color = JColorChooser.showDialog(MainFrame.getInstance(), "Choose a color", Color.BLACK);
			if (color != null) {
				int r, g, b, a;
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				a = color.getAlpha();
				int rgba = (a << 24) | (r << 16) | (g << 8) | b;
				for (TermPainter tp : terms) {
					Term term = tp.getTerm();
					term.setColor(rgba);
					term.publish();
				}
				((ProjectView)MainFrame.getInstance().getProjectView()).getMindMapView().repaint();
			}
		} else {
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(int x, int y, MindMapView view) {
		Term term = view.getMindMap().getTermAt(x, y);
		if(term == null)
			return;
		Color color = JColorChooser.showDialog(MainFrame.getInstance(), "Choose a color", Color.BLACK);
		if (color != null) {
			int r, g, b, a;
			r = color.getRed();
			g = color.getGreen();
			b = color.getBlue();
			a = color.getAlpha();
			int rgba = (a << 24) | (r << 16) | (g << 8) | b;
			term.setColor(rgba);
			term.publish();
			view.repaint();
		}
	}
}

