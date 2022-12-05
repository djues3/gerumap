package raf.dsw.gerumap.app.gui.state;

import raf.dsw.gerumap.app.mapRepository.model.MindMap;

public abstract class State {
	public void mousePressed(int x, int y, MindMap map) {}
	public void mouseDragged(int x, int y, MindMap map) {}
}
