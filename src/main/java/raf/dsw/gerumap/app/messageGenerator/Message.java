package raf.dsw.gerumap.app.messageGenerator;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

	private Level level;
	private String message;
	private String timestamp = ZonedDateTime
		.now(ZoneId.systemDefault())
		.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss.SSS"));

	@Override
	public String toString() {
		return message;
	}

	public enum Level {
		DEBUG,
		INFO,
		WARNING,
		ERROR
	}
}
