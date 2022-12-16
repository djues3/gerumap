package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.state.states.ZoomState;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
@NoArgsConstructor
public class TermPainter extends Painter implements ISubscriber  {
	private Color color;
	private Shape shape;
	private Term term;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private float x;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private float y;
	private boolean selected = false;

	public TermPainter(Term term) {
		this.term = term;
		this.x = term.getX() - term.getWidth() / 2.0f;
		this.y = term.getY() - term.getHeight() / 2.0f;
		color = new Color(term.getColor());
		term.addSubscriber(this);
	}

	@Override
	public void draw(Graphics g) {
		setup();
		createGraphic((Graphics2D) g);
	}
	private void createGraphic(Graphics2D g2d) {
		ZoomState zoomState = null;
		if (MainFrame.getInstance().getPvm().getProjectView() instanceof ProjectView pv)
			zoomState = pv.getStateManager().getZoomState();
		if (zoomState != null)
			g2d.setTransform(zoomState.getAffineTransform());
		createGraphic(g2d, color);
	}
	private void createGraphic(Graphics2D g2d, Color color) {
		this.color = color;
		if(selected) {
			g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() * 39 / 100));
			g2d.fill(shape);
		} else {
			g2d.setColor(color);
			g2d.fill(shape);
		}
		g2d.setColor(new Color(25, 63, 148, 255));
		g2d.setStroke(new BasicStroke(2));
		g2d.setFont(new Font("Arial", Font.PLAIN, 12));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(shape);
		x = term.getX() - Term.DEFAULT_WIDTH / 2.0f;
		y = term.getY() - Term.DEFAULT_HEIGHT / 2.0f;
		g2d.drawString(term.getText(), x + 20f, y + 27.5f);
	}

	@Override
	public boolean elementAt(int x, int y) {
		return shape.intersects(new Rectangle2D.Double(x - Term.DEFAULT_WIDTH / 2d, y - Term.DEFAULT_HEIGHT / 2d,
				Term.DEFAULT_WIDTH, Term.DEFAULT_HEIGHT));
	}

	private void setup() {
		setup(Term.DEFAULT_WIDTH, Term.DEFAULT_HEIGHT);
	}

	private void setup(int width, int height) {
		shape = new Ellipse2D.Double(term.getX() - width / 2.0f,term.getY() - height / 2.0f, width, height);
	}
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		createGraphic((Graphics2D) g);
	}

	@Override
	public void update(IPublisher publisher) {
		if(publisher instanceof Term term) {
			this.term = term;
			this.x = term.getX() - term.getWidth() / 2.0f;
			this.y = term.getY() - term.getHeight() / 2.0f;
			color = new Color(term.getColor());
		}
	}
}
