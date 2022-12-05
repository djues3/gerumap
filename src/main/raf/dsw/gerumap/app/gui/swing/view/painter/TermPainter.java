package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class TermPainter extends Painter {
	Term term;

	@Override
	public void draw(Graphics g) {
		draw(g, Term.DEFAULT_WIDTH, Term.DEFAULT_HEIGHT);
	}
	public void draw(Graphics g, int width, int height) {
		g.setColor(Color.WHITE);
		g.drawOval(term.getX(), term.getY(), width, height);
		g.fillOval(term.getX(), term.getY(), width, height);
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString(term.getText(), term.getX() + 10, term.getY() + 15);
	}
}
