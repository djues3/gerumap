package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
@NoArgsConstructor
public class LinkPainter extends Painter {
	Shape shape;
	Link link;

	public LinkPainter(Link link) {
		this.link = link;
	}
	@Override
	public void draw(Graphics g) {
		setup();
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(shape);
	}

	private void setup() {
		shape = new Line2D.Double(link.getFrom().getX(), link.getFrom().getY(), link.getTo().getX(), link.getTo().getY());
	}

	@Override
	public boolean elementAt(int x, int y) {
		return shape.contains(x, y);
	}
}


