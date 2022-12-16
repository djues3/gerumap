package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;

import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;

@Getter
@Setter
public class SelectionState extends State {
	private List<TermPainter> selectedTerms;
	private List<LinkPainter> selectedLinks;
	private Integer startX, startY, startXReal, startYReal;
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			view.getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		startX = x;
		startY = y;
		x = (int)real.getX();
		y = (int)real.getY();
		startXReal = x;
		startYReal = y;
		selectedTerms = new ArrayList<>();
		selectedLinks = new ArrayList<>();
		for(Painter p : view.getPainters()) {
			if(p instanceof TermPainter tp) {
				tp.setSelected(false);
			}
			if(p instanceof LinkPainter lp) {
				lp.setSelected(false);
			}
		}
		view.repaint();
	}

	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			view.getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		view.getGraphics()
				.drawRect(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y));
		x = (int)real.getX();
		y = (int)real.getY();
		selectedTerms = view.getTermsInRectangle(
				new Rectangle2D.Double(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y)));
		for(TermPainter tp : selectedTerms) {
			tp.setSelected(true);
		}
		selectedLinks = view.getLinksInRectangle(
				new Rectangle2D.Double(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y)));
		for(LinkPainter lp : selectedLinks) {
			lp.setSelected(true);
		}
		for(Painter p : view.getPainters()) {
			if(p instanceof TermPainter tp) {
				if(!selectedTerms.contains(tp)) {
					tp.setSelected(false);
				}
			}
			if(p instanceof LinkPainter lp) {
				if(!selectedLinks.contains(lp)) {
					lp.setSelected(false);
				}
			}
		}
		view.repaint();
	}

	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			view.getAffineTransform().inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
		x = (int)real.getX();
		y = (int)real.getY();
		selectedTerms = view.getTermsInRectangle(
				new Rectangle2D.Double(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y)));
		selectedLinks = view.getLinksInRectangle(
				new Rectangle2D.Double(min(startX, x), min(startY, y), abs(startX - x), abs(startY - y)));
		view.repaint();
	}
}
