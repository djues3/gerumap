package raf.dsw.gerumap.app.gui.swing.observer;

public interface IPublisher {
	void addSubscriber(ISubscriber subscriber);
	void removeSubscriber(ISubscriber subscriber);
	void publish();

}
