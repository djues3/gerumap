package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.states.ZoomState;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
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
	private boolean selected;

	public LinkPainter(Link link) {
		this.link = link;
	}
	@Override
	public void draw(Graphics g) {
		setup();
		Graphics2D g2d = (Graphics2D) g;
		if(selected) {
			g2d.setColor(Color.RED);
		} else {
			g2d.setColor(Color.BLACK);
		}
		g2d.draw(shape);
	}

	private void setup() {
		Component tmp= ((Component) MainFrame.getInstance().getPvm().getProjectView());
		ZoomState zoomState = null;
		if (tmp instanceof ProjectView)
			zoomState = ((ProjectView)tmp).getStateManager().getZoomState();
		shape = new Line2D.Double(link.getFrom().getX() + zoomState.getOffsetX(), link.getFrom().getY() + zoomState.getOffsetY(), link.getTo().getX() + zoomState.getOffsetX(), link.getTo().getY() + zoomState.getOffsetY());
	}

	@Override
	public boolean elementAt(int x, int y) {
		return shape.intersects(new Rectangle2D.Double(x - 5d, y - 5d, 10d, 10d));
	}
}


