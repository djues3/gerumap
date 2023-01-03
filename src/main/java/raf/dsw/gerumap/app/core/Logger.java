package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.observer.ISubscriber;

public interface Logger extends ISubscriber {

	void log(Message message);

	void log(Throwable t);

	void log(Message message, Throwable t);

	String format(Message message, Throwable t);

	boolean checkDiscard(Message message);

	enum Level {
		// Minimum level of message logged, in order of increasing severity. Off means no logging.
		DEBUG,
		INFO,
		WARNING,
		ERROR,
		OFF
	}
}
