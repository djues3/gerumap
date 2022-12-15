package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
@NoArgsConstructor
public class TermPainter extends Painter {
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
	}

	@Override
	public void draw(Graphics g) {
		setup();
		createGraphic((Graphics2D) g);
	}
	private void createGraphic(Graphics2D g2d) {
		if(selected) {
			g2d.setColor(Color.RED);
			g2d.fill(shape);
		} else {
			g2d.setColor(Color.WHITE);
			g2d.fill(shape);
		}
		g2d.setColor(new Color(25, 63, 148));
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
		shape = new Ellipse2D.Double(term.getX() - width / 2.0f, term.getY() - height / 2.0f, width, height);
	}

	public void draw(Graphics g, int width, int height) {
		setup(width, height);
		createGraphic((Graphics2D) g);
	}
}
