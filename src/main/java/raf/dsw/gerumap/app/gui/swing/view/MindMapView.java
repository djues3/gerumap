package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.StateManager;
import raf.dsw.gerumap.app.gui.swing.controller.MouseController;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;
import raf.dsw.gerumap.app.observer.IPublisher;
import raf.dsw.gerumap.app.observer.ISubscriber;


@Getter
@Setter
public class MindMapView extends JPanel implements ISubscriber {

	private MindMap mindMap;
	private StateManager stateManager;

	private AffineTransform affineTransform;

	private Set<Painter> painters = new HashSet<>();
	private Shape tempShape; // used for drawing the selection rectangle and lines for links.

	public MindMapView(MindMap mindMap) {
		affineTransform = new AffineTransform();
		affineTransform.setToIdentity();
		this.mindMap = mindMap;
		MouseController mouseController = new MouseController(this);
		this.addMouseListener(mouseController);
		this.addMouseMotionListener(mouseController);
		this.addMouseWheelListener(mouseController);
		this.mindMap.addSubscriber(this);
		for (MapNode node : mindMap.getChildren()) {
			if (node instanceof Term term) {
				painters.add(new TermPainter(term, this));
			} else if (node instanceof Link link) {
				painters.add(new LinkPainter(link, this));
			}
		}
		repaint();
	}

	@Override
	public void update(IPublisher publisher) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Painter painter : painters) {
			if (painter instanceof LinkPainter lp) {
				lp.draw(g);
			}
		}
		for (Painter painter : painters) {
			if (painter instanceof TermPainter tp) {
				tp.draw(g);
			}
		}
		float[] dash = {8f, 8f};
		g.setColor(new Color(25, 63, 148, 255));
		((Graphics2D) g).setStroke(
			new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1, dash, 1));
		if (tempShape != null) {
			((Graphics2D) g).draw(tempShape);
		}
	}

	public void addPainter(Painter painter) {
		painters.add(painter);
	}

	public void removePainter(Painter painter) {
		if (painter instanceof TermPainter tp) {
			mindMap.getChildren().remove(tp.getTerm());
		}
		if (painter instanceof LinkPainter lp) {
			mindMap.getChildren().remove(lp.getLink());
		}
		painters.remove(painter);
	}

	public List<TermPainter> getTermsInRectangle(Rectangle2D.Double bounds) {
		List<TermPainter> terms = new ArrayList<>();
		for (Painter painter : painters) {
			if (painter instanceof TermPainter tp) {
				if (tp.getShape().intersects(bounds) || bounds.contains(
					tp.getShape().getBounds())) {
					terms.add(tp);
				}
			}
		}
		return terms;
	}


	public List<LinkPainter> getLinksInRectangle(Rectangle2D.Double aDouble) {
		List<LinkPainter> links = new ArrayList<>();
		for (Painter painter : painters) {
			if (painter instanceof LinkPainter lp) {
				if (lp.getShape().intersects(aDouble) || aDouble.contains(
					lp.getShape().getBounds())) {
					links.add(lp);
				}
			}
		}
		return links;
	}

	public TermPainter getPainterForTerm(Term term) {
		for (Painter painter : painters) {
			if (painter instanceof TermPainter tp) {
				if (tp.getTerm().equals(term)) {
					return tp;
				}
			}
		}
		return null;
	}

	public Painter getPainterForLink(Link link) {
		for (Painter painter : painters) {
			if (painter instanceof LinkPainter lp) {
				if (lp.getLink().equals(link)) {
					return lp;
				}
			}
		}
		return null;
	}

	public void rearrange(Term term) {
		mindMap.rearrange(term);
	}
}
