package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
@NoArgsConstructor
public class LinkPainter extends Painter {
	Shape shape;
	Link link;

	private MindMapView mindMapView;
	private boolean selected;

	public LinkPainter(Link link, MindMapView mindMapView) {
		this.mindMapView = mindMapView;
		this.link = link;
	}
	@Override
	public void draw(Graphics g) {
		setup();
		Graphics2D g2d = (Graphics2D) g;
		((Graphics2D) g).setTransform(mindMapView.getAffineTransform());
		if(selected) {
			g2d.setColor(new Color(255,0,0, 78));
		} else {
			g2d.setColor(Color.BLACK);
		}
		g2d.setStroke(new BasicStroke(2));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(shape);
	}

	private void setup() {
		shape = new Line2D.Double(link.getFrom().getX(), link.getFrom().getY(), link.getTo().getX(), link.getTo().getY());
	}

	@Override
	public boolean elementAt(int x, int y) {
		return shape.intersects(new Rectangle2D.Double(x - 5d, y - 5d, 10d, 10d));
	}
}


