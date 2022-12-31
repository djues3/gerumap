package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.swing.view.EditDialog;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class EditState extends State {
	public boolean tryToEdit() {
		ProjectView pv = (ProjectView) MainFrame.getInstance().getProjectView();
		List<TermPainter> terms = pv.getStateManager().getSelectedTerms();
		if (terms == null)
			return false;
		if (terms.isEmpty())
			return false;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		JDialog dialog = new JDialog();
		EditDialog edit = new EditDialog();
		edit.getTextField().setText(terms.get(0).getTerm().getText());
		dialog.setModal(true);
		dialog.setSize(screenSize.width / 3, screenSize.height / 3);
		dialog.setContentPane(edit);
		dialog.getRootPane().setDefaultButton(edit.getDoneButton());
		dialog.setLocationRelativeTo(MainFrame.getInstance());
		dialog.setVisible(true);
		Color color = edit.getColor();
		String text = edit.getText();
		if (color != null) {
			int rgba = getRGBA(color);
			for (TermPainter tp : terms) {
				Term term = tp.getTerm();
				term.setColor(rgba);
				term.publish();
			}
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().repaint();
		}
		if (text != null && !text.equals("")) {
			for (TermPainter tp : terms) {
				Term term = tp.getTerm();
				term.setText(text);
				term.publish();
			}
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().repaint();
		}
		return true;
	}
	private int getRGBA(Color color) {
		int r, g, b, a;
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		a = color.getAlpha();
		return  (a << 24) | (r << 16) | (g << 8) | b;
	}

	@Override
	public void mouseClicked(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int) real.getX();
		y = (int) real.getY();
		Term term = view.getMindMap().getTermAt(x, y);
		if(term == null)
			return;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		JDialog dialog = new JDialog();
		EditDialog edit = new EditDialog();
		edit.getTextField().setText(term.getText());
		dialog.setModal(true);
		dialog.setSize(screenSize.width / 3, screenSize.height / 3);

		dialog.setContentPane(edit);
		dialog.setLocationRelativeTo(MainFrame.getInstance());

		dialog.setVisible(true);

		Color color = edit.getColor();
		String text = edit.getText();

		if (color != null) {
			term.setColor(getRGBA(color));
			term.publish();
			view.repaint();
		}
		if (text != null && !text.equals("")) {
			term.setText(text);
			term.publish();
			view.repaint();
		}
	}
}

