package raf.dsw.gerumap.app.messageGenerator;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.core.MessageGenerator;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;
@Getter
@Setter
public class MessageGeneratorImpl extends IPublisherImpl implements MessageGenerator {
	Message message;
	Throwable throwable;
	@Override
	public void generate(String msg, Message.Level level) {
		message = new Message();
		message.setMessage(msg);
		message.setLevel(level);
		publish();
	}

	@Override
	public void generate(String msg, Message.Level level, Throwable t) {
		message = new Message();
		message.setMessage(msg);
		message.setLevel(level);
		throwable = t;
		publish();
	}

	public void init() {
		this.addSubscriber(AppCore.getInstance().getLogger());
		this.addSubscriber(AppCore.getInstance().getGui());
	}
}

