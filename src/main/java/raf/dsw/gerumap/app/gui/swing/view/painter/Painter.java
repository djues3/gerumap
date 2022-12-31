package raf.dsw.gerumap.app.gui.swing.view.painter;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public abstract class Painter extends JComponent {
	protected boolean selected = false;

	public abstract void draw(Graphics g);

	public abstract boolean elementAt(int x, int y);
}
