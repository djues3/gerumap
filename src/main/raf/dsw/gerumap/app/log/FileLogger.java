package raf.dsw.gerumap.app.log;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.messageGenerator.Message;
import java.io.*;


@Getter
@Setter
public class FileLogger extends AbstractLogger {


	public static final String DEFAULT_FILE_PATH = "/src/resources/log.txt";
	public static final String PROJECT_PATH = System.getProperty("user.dir");

	private String path = DEFAULT_FILE_PATH;

	@Override
	public void log(Message message) {
		log(message, null);
	}

	@Override
	public void log(Throwable t) {
		append(format(null, t));
	}

	@Override
	public void log(Message message, Throwable t) {
		if (checkDiscard(message)) return;
		append(format(message, t));
	}
	private void append(String format) {
		try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir")
				+ "/src/resources/log.log", true)))) {
			pr.println(format);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
