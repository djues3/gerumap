package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.messageGenerator.Message;

public interface MessageGenerator {

	void generate(Throwable t);

	void generate(String msg, Message.Level level);

	void generate(String msg, Message.Level level, Throwable t);

	void init();


}
