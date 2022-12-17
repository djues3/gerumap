package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.state.states.ZoomState;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import javax.swing.*;
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
	private MindMapView mindMapView;

	public TermPainter(Term term, MindMapView mindMapView) {
		this.term = term;
		this.mindMapView = mindMapView;
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
		g2d.setTransform(mindMapView.getAffineTransform());
		createGraphic(g2d, color);
	}

	protected void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}

	private void createGraphic(Graphics2D g2d, Color color) {
		this.color = color;

		g2d.setColor(color);
		g2d.fill(shape);
		g2d.setColor(new Color(25, 63, 148, 255));
		float[] dash = {10f, 10f};
		if (selected) {
			g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, dash, 1));
		} else {
			g2d.setStroke(new BasicStroke(2));
		}
		g2d.setFont(new Font("Arial", Font.PLAIN, 12));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(shape);
		x = term.getX() - Term.DEFAULT_WIDTH / 2.0f;
		y = term.getY() - Term.DEFAULT_HEIGHT / 2.0f;
//		g2d.drawString(term.getText(), x + 20f, y + 27.5f);

		String text = term.getText();
		FontMetrics fm = g2d.getFontMetrics();

		int x = (getWidth() - fm.stringWidth(text)) / 2;
		int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

		g2d.drawString(text, term.getX() + x, term.getY() + y);
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
