package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.messageGenerator.Message;

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
