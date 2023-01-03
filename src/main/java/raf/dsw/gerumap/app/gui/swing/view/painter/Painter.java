package raf.dsw.gerumap.app.gui.swing.view.painter;

import java.awt.Graphics;
import javax.swing.JComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Painter extends JComponent {

	protected boolean selected = false;

	public abstract void draw(Graphics g);

	public abstract boolean elementAt(int x, int y);
}
