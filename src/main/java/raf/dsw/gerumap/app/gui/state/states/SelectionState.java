package raf.dsw.gerumap.app.gui.state.states;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;

@Getter
@Setter
public class SelectionState extends State {

	private List<TermPainter> selectedTerms = new ArrayList<>();
	private List<LinkPainter> selectedLinks = new ArrayList<>();
	private Integer startX, startY, startXReal, startYReal;

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		startX = x;
		startY = y;
		x = (int) real.getX();
		y = (int) real.getY();
		startXReal = x;
		startYReal = y;
		clearSelected();
		view.repaint();
	}

	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		view.setTempShape(
			new Rectangle(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y)));
		x = (int) real.getX();
		y = (int) real.getY();
		setSelected(x, y, view);
		view.repaint();
	}

	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int) real.getX();
		y = (int) real.getY();
		setSelected(x, y, view);
		view.setTempShape(null);
		view.repaint();
	}

	@Override
	public void mouseClicked(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int) real.getX();
		y = (int) real.getY();
		setSelected(x, y, view);
		view.repaint();
	}

	private void setSelected(int x, int y, MindMapView view) {
		selectedTerms = view.getTermsInRectangle(
			new Rectangle2D.Double(min(startXReal, x), min(startYReal, y), abs(startXReal - x),
				abs(startYReal - y)));
		selectedLinks = view.getLinksInRectangle(
			new Rectangle2D.Double(min(startXReal, x), min(startYReal, y), abs(startXReal - x),
				abs(startYReal - y)));
		for (Painter p : view.getPainters()) {
			p.setSelected(selectedTerms.contains(p) || selectedLinks.contains(p));
		}
	}

	public void clearSelected() {
		for (TermPainter tp : selectedTerms) {
			tp.setSelected(false);
		}
		for (LinkPainter lp : selectedLinks) {
			lp.setSelected(false);
		}
		selectedTerms = new ArrayList<>();
		selectedLinks = new ArrayList<>();
	}
}
