package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class LinkState extends State {
	private Integer startX, startY;
	private Link link = new Link();

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Term term = view.getMindMap().getTermAt(x, y);
		if (term != null) {
			startX = x;
			startY = y;
			if (link.getFrom() == null) {
				link.setFrom(term);
			}
		}
	}
	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		if(startX == null || startY == null)
			return;
		Graphics g = view.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(startX, startY, x, y);
		view.repaint();
	}
	@Override
	public void mouseReleased(int x, int y, MindMapView view) {
		if(link.getFrom() == null)
			return;
		Term term = view.getMindMap().getTermAt(x, y);
		if(term == null)
			return;
		link.setTo(term);
		link.getTo().addLink(link.getFrom());
		link.getFrom().addLink(link.getTo());
		view.getMindMap().addChild(link);
		view.addPainter(new LinkPainter(link));
		startX = null;
		startY = null;
		link = new Link();
		view.repaint();
	}
}

