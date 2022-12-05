package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.Element;


@Getter
@Setter

public class Term extends Element {
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	public Term() {
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
	}
}
