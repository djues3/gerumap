package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter
@NoArgsConstructor
public class LinkState extends State {
	private Integer startX, startY, startXReal, startYReal;
	private Link link = new Link();

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		Term term = view.getMindMap().getTermAt((int)real.getX(), (int)real.getY());
		if (term != null) {
			startX = x;
			startY = y;
			x = (int)real.getX();
			y = (int)real.getY();
			startXReal = x;
			startYReal = y;
			if (link.getFrom() == null) {
				link.setFrom(term);
			}
		}
	}
	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
//		x = (int)real.getX();
//		y = (int)real.getY();
		if(startX == null || startY == null)
			return;
		Graphics g = view.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(startX, startY, x, y);
		view.repaint();
	}
	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		Point2D real = mapPoints(x, y, view.getAffineTransform());
		x = (int)real.getX();
		y = (int)real.getY();
		if(link.getFrom() == null)
			return;
		Term term = view.getMindMap().getTermAt(x, y);
		if(term == null)
			return;
		link.setTo(term);
		link.getTo().addLink(link.getFrom());
		link.getFrom().addLink(link.getTo());
		view.getMindMap().addChild(link);
		view.addPainter(new LinkPainter(link, view));
		startX = null;
		startY = null;
		link = new Link();
		view.repaint();
	}
}

