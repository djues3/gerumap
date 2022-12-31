package raf.dsw.gerumap.app.log;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.messageGenerator.Message;


@Getter
@Setter
public class ConsoleLogger extends AbstractLogger {

	@Override
	public void log(Message message, Throwable throwable) {
		if (checkDiscard(message)) return;
		String format = format(message, throwable);
		if(message.getLevel() == Message.Level.ERROR)
			System.err.println(format);
		else
			System.out.println(format);
	}

	@Override
	public void log(Message message) {
		if (checkDiscard(message)) return;
		String format = format(message, null);
		if (message.getLevel().equals(Message.Level.ERROR))
			System.err.println(format);
		else
			System.out.println(format);
	}
	@Override
	public void log(Throwable t) {
		System.err.println(format(null, t));
	}
}
