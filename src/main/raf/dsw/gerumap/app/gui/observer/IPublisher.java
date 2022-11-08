package raf.dsw.gerumap.app.gui.observer;

public interface IPublisher {
	void addSubscriber(ISubscriber subscriber);
	void removeSubscriber(ISubscriber subscriber);
	void publish();

}