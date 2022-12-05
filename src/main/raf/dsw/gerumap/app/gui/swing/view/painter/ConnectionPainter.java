package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Connection;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionPainter extends Painter {
	Connection connection;
	@Override
	public void draw(Graphics g) {
		g.drawLine(connection.getFrom().getX(), connection.getFrom().getY(), connection.getTo().getX(), connection.getTo().getY());
	}
}


