package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class LinkPainter extends Painter {
	Link link;
	@Override
	public void draw(Graphics g) {
		g.drawLine(link.getFrom().getX(), link.getFrom().getY(), link.getTo().getX(), link.getTo().getY());
	}
}


