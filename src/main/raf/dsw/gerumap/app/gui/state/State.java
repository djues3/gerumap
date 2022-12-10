package raf.dsw.gerumap.app.gui.state;

import raf.dsw.gerumap.app.gui.swing.view.MindMapView;


public abstract class State {
	public void mousePressed(int x, int y, MindMapView map) {}
	public void mouseDragged(int x, int y, MindMapView map) {}
}
