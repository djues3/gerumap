package raf.dsw.gerumap.app.log;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.core.Logger;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageGeneratorImpl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public abstract class AbstractLogger implements Logger {
	// Minimalni level za logovanje
	Level level = Level.INFO;

	@Override
	public boolean checkDiscard(Message message) {
		return message != null && (message.getLevel().ordinal() < level.ordinal());
	}
	@Override
	public void update(IPublisher publisher) {
		if(publisher instanceof MessageGeneratorImpl) {
			MessageGeneratorImpl generator = (MessageGeneratorImpl) publisher;
			if (generator.getThrowable() == null)
				if (generator.getMessage() != null) {
					log(((MessageGeneratorImpl) publisher).getMessage());
					return;
				}
			else if (generator.getMessage() == null) {
					log(generator.getThrowable());
					return;
				}
			log(generator.getMessage(), generator.getThrowable());
		}
	}
	@Override
	public String format(Message message, Throwable throwable) {
		String timestamp = ZonedDateTime
				.now(ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss.SSS"));
		if (message == null) {
			if (throwable == null) {
				return "";
			}
			StringBuilder stackTrace = new StringBuilder();
			for (StackTraceElement stackTraceElement : throwable.getStackTrace())
				stackTrace.append("	   at ").append(stackTraceElement.toString()).append("\n");
			return timestamp + " [" + Level.ERROR.name() + "] " + " - [EXCEPTION] - " +
					throwable.getClass().getName() + " [CAUSE] " + throwable.getMessage() +  " -\n" + stackTrace;
		}
		if (throwable == null) {
			return timestamp + " [" + message.getLevel().name() + "] " + message.getMessage();
		}
		StringBuilder stackTrace = new StringBuilder();
		for (StackTraceElement stackTraceElement : throwable.getStackTrace())
			stackTrace.append("	   at ").append(stackTraceElement.toString()).append("\n");
		return timestamp + " [" + message.getLevel().name() + "] " + message.getMessage() + " - [EXCEPTION] - " +
				throwable.getClass().getName() + " [CAUSE] " + throwable.getMessage() + " -\n[STACKTRACE] : \n" + stackTrace;
	}
}
