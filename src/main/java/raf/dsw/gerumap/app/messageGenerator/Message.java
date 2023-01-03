package raf.dsw.gerumap.app.messageGenerator;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {

	private Level level;
	private String message;
	private String timestamp = ZonedDateTime
		.now(ZoneId.systemDefault())
		.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss.SSS"));

	public enum Level {
		DEBUG,
		INFO,
		WARNING,
		ERROR
	}
}
