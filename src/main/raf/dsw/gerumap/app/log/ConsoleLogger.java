package raf.dsw.gerumap.app.log;

import raf.dsw.gerumap.app.core.Logger;
import raf.dsw.gerumap.app.core.MessageGenerator;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.messageGenerator.Message;

public class ConsoleLogger implements Logger {



	@Override
	public void log(Message message, Level level) {
		if (level == Level.ERROR)
			System.err.println(message.toString());
		else
			System.out.println(message.toString());
	}

	@Override
	public void log(Message message) {
		log(message,Level.INFO);
	}

	@Override
	public void update(IPublisher publisher) {
		if(publisher instanceof MessageGenerator)
			log(((MessageGenerator) publisher).generate());
	}
}
