package raf.dsw.gerumap.app.gui.swing.view.painter;

import java.awt.*;

public abstract class Painter extends Component {

	public abstract void draw(Graphics g);

	public abstract boolean elementAt(int x, int y);
}
