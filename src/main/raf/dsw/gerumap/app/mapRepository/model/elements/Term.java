package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.Element;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Term extends Element {
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 50;
	private int x;
	private int y;
	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT;
	private String text;
	private List<Link> links = new ArrayList<>();

	public void addLink(Term term) {
		links.add(new Link(this, term));
	}

	public boolean contains(int x, int y) {
		return (x >= (this.x - DEFAULT_WIDTH / 2)) && (y >= (this.y - DEFAULT_HEIGHT / 2)) &&
				(x <= (this.x - DEFAULT_WIDTH / 2 + width)) && (y <= (this.y - DEFAULT_HEIGHT / 2 + height));
	}
}
