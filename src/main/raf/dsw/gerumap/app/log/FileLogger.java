package raf.dsw.gerumap.app.log;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.core.Logger;
import raf.dsw.gerumap.app.core.MessageGenerator;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.messageGenerator.Message;

import java.io.*;

@Getter
@Setter
public class FileLogger implements Logger {

	public static final String DEFAULT_FILE_PATH = "log.txt";

	private String path;
	@Override
	public void log(Message message, Level level) {
		File file = new File("append.txt");
		FileWriter fr;
		BufferedWriter br;
		PrintWriter pr;
		try {
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			pr.println("data");
			pr.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void log(Message message) {
		log(message, Level.INFO);
	}

	@Override
	public void update(IPublisher publisher) {
		if(publisher instanceof MessageGenerator)
			log(((MessageGenerator) publisher).generate());
		else throw new RuntimeException("");
	}
}
