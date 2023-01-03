package raf.dsw.gerumap.app.gui.state.states;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;

@Getter
@Setter
public class ZoomState extends State {

	private AffineTransform affineTransform;
	private Float startX = 0f;
	private Float startY = 0f;
	private Float offsetX = 0f;
	private Float offsetY = 0f;
	private Float scale = 1.0f;
	private Float zoomPower;

	public ZoomState() {
	}

	public Float getOffsetX() {
		return offsetX;
	}

	public Float getOffsetY() {
		return offsetY;
	}

	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		affineTransform = view.getAffineTransform();
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			affineTransform.inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		startX = (float) x - offsetX;
		startY = (float) y - offsetY;
	}

	@Override
	public void mouseDragged(int x, int y, MindMapView view) {
		affineTransform = view.getAffineTransform();
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			affineTransform.inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		offsetX = (float) x - startX;
		offsetY = (float) y - startY;
//        affineTransform.translate(offsetX, offsetY);
		affineTransform.setToTranslation(offsetX, offsetY);
		affineTransform.scale(scale, scale);
		view.repaint();
	}

	@Override
	public void mouseWheelMoved(int x, int y, int step, MindMapView view) {
		affineTransform = view.getAffineTransform();
		Point2D real = new Point2D.Double();
		Point2D screen = new Point2D.Double(x, y);
		try {
			affineTransform.inverseTransform(screen, real);
		} catch (NoninvertibleTransformException e) {
			AppCore.getInstance().getLogger().log(e);
		}
		scale += step * .05f;
		scale = Math.max(scale, .2f);
		affineTransform.setToTranslation(real.getX() - scale * real.getX(),
			real.getY() - scale * real.getY());
		affineTransform.scale(scale, scale);
		view.repaint();
	}
}