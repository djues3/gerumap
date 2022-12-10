package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.Element;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Term extends Element {
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 50;
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	private List<Link> links;

	public void addLink(Term term) {
		links.add(new Link(this, term));
	}
}
