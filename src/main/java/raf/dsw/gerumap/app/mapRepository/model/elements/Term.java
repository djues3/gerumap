package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.*;
import raf.dsw.gerumap.app.mapRepository.model.Element;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
public class Term extends Element {
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 50;
	public static final int DEFAULT_COLOR = 0xC8BAFAFF;
	private int color = DEFAULT_COLOR; //  0xAARRGGBB
	private int x;
	private int y;
	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT;
	private String text;
	@ToString.Exclude
	private transient List<Link> links = new ArrayList<>();

	public void addLink(Term term) {
		links.add(new Link(this, term));
	}

	public boolean contains(int x, int y) {
		return (x >= (this.x - DEFAULT_WIDTH / 2)) && (y >= (this.y - DEFAULT_HEIGHT / 2)) &&
				(x <= (this.x - DEFAULT_WIDTH / 2 + width)) && (y <= (this.y - DEFAULT_HEIGHT / 2 + height));
	}
}
