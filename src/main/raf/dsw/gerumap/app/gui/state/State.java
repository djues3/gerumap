package raf.dsw.gerumap.app.gui.state;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.messageGenerator.Message;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;


public abstract class State {
	public void mousePressed(int x, int y, MindMapView view) {}
	public void mouseDragged(int x, int y, MindMapView view) {}

	public void mouseClicked(int x, int y, MindMapView view) {}
	public void mouseReleased(int x, int y, MindMapView view) {}

	public void mouseMoved(int x, int y, MindMapView view) {}
	public void mouseWheelMoved(int x, int y, int step, MindMapView view) {}
	public Point2D mapPoints(int x, int y, AffineTransform transform) {
		Point2D dest = new Point2D.Double();
		Point2D src = new Point2D.Double(x, y);
		try {
			transform.inverseTransform(src, dest);
			return dest;
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getMessageGenerator().generate(e.getMessage(), Message.Level.ERROR, e);
		}
		return null;
	}
}
