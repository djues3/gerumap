package raf.dsw.gerumap.app.gui.state;

import raf.dsw.gerumap.app.gui.swing.view.MindMapView;


public abstract class State {
	public void mousePressed(int x, int y, MindMapView view) {}
	public void mouseDragged(int x, int y, MindMapView view) {}

	public void mouseClicked(int x, int y, MindMapView view) {}
	public void mouseReleased(int x, int y, MindMapView view) {}

	public void mouseMoved(int x, int y, MindMapView view) {}
	public void mouseWheelMoved(int x, MindMapView view) {}
}
