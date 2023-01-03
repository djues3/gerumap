package raf.dsw.gerumap.app.observer;

import java.util.ArrayList;
import java.util.List;
import lombok.ToString;


@ToString
public class IPublisherImpl implements IPublisher {

	protected transient final List<ISubscriber> subscribers = new ArrayList<>();

	public void addSubscriber(ISubscriber subscriber) {
		if (subscriber != null && !subscribers.contains(subscriber)) {
			subscribers.add(subscriber);
		}
	}

	public void removeSubscriber(ISubscriber subscriber) {
		if (subscriber != null) {
			subscribers.remove(subscriber);
		}
	}

	@Override
	public void publish() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update(this);
		}
	}
}
