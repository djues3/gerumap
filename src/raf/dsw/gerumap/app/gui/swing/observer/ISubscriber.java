package raf.dsw.gerumap.app.gui.swing.observer;

public interface ISubscriber {
	void update();
	void update(IPublisher publisher);
}
