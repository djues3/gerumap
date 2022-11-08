package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.messageGenerator.Message;

public interface MessageGenerator extends IPublisher {
	Message generate();
}
