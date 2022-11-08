package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.messageGenerator.Message;

public interface Logger extends ISubscriber {

	void log(Message message, Level level);
	void log(Message message);

	enum Level {
		// Minimum level of message logged, in order of increasing severity. Off means no logging.
		DEBUG,
		INFO,
		WARNING,
		ERROR,
		OFF
	}
}
