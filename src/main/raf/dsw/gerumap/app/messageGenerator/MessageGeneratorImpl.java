package raf.dsw.gerumap.app.messageGenerator;

import raf.dsw.gerumap.app.core.MessageGenerator;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;

public class MessageGeneratorImpl extends IPublisherImpl implements MessageGenerator {

	@Override
	public Message generate() {
		return new Message();
	}

}

