package raf.dsw.gerumap.app.gui.observer;

import java.util.ArrayList;
import java.util.List;


public class IPublisherImpl implements IPublisher{
	protected final List<ISubscriber> subscribers = new ArrayList<>();

	public void addSubscriber(ISubscriber subscriber) {
		if (subscriber != null && !subscribers.contains(subscriber))
			subscribers.add(subscriber);
	}

	public void removeSubscriber(ISubscriber subscriber) {
		if (subscriber != null)
			subscribers.remove(subscriber);
	}

	public void publish() {
		for(ISubscriber subscriber: subscribers)
			subscriber.update(this);
	}
}
