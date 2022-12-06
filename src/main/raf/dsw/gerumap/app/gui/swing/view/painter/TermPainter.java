package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
@Setter
@NoArgsConstructor
public class TermPainter extends Painter {
	private Shape shape;
	private Term term;

	@Override
	public void draw(Graphics g) {
		setup(Term.DEFAULT_WIDTH, Term.DEFAULT_HEIGHT);
		createGraphic((Graphics2D) g);
	}

	private void createGraphic(Graphics2D g2d) {
		g2d.setColor(new Color(25, 63, 148));
		g2d.setStroke(new BasicStroke(2));
		g2d.setFont(new Font("Arial", Font.PLAIN, 12));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(shape);
	}

	@Override
	public boolean elementAt(int x, int y) {
		return shape.contains(x, y);
	}

	private void setup() {
		setup(Term.DEFAULT_WIDTH, Term.DEFAULT_HEIGHT);
	}

	private void setup(int width, int height) {
		shape = new Ellipse2D.Double(term.getX(), term.getY(), width, height);
	}

	public void draw(Graphics g, int width, int height) {
		setup(width, height);
		createGraphic((Graphics2D) g);
	}
}
