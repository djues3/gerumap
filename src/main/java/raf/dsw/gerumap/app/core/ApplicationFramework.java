package raf.dsw.gerumap.app.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class ApplicationFramework {

	protected GUI gui;
	protected MapRepository mapRepository;
	protected Logger logger;
	protected MessageGenerator messageGenerator;
	protected Serializer serializer;

	public abstract void run();

	public void init(GUI gui, MapRepository mapRepository, Logger logger,
		MessageGenerator messageGenerator, Serializer serializer) {
		this.gui = gui;
		this.mapRepository = mapRepository;
		this.logger = logger;
		this.messageGenerator = messageGenerator;
		this.serializer = serializer;
	}
}
