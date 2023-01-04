package raf.dsw.gerumap.app.mapRepository.model.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.mapRepository.model.Element;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Term extends Element {

	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 50;
	public static final int DEFAULT_COLOR = 0xC8BAFAFF;
	private int color = DEFAULT_COLOR; //  0xAARRGGBB
	private int x;
	private int y;
	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT;
	@Setter(AccessLevel.NONE)
	private boolean centralTerm;
	private String text;
	@ToString.Exclude
	private transient List<Link> links = new ArrayList<>();

	public Term() {
		name = "Term" + new Random().nextInt(100000);
	}


	public void setCentralTerm(boolean centralTerm) {
		if (!this.centralTerm && !centralTerm) {
			return;
		}
		this.centralTerm = centralTerm;
		this.setWidth(centralTerm ? width * 5 / 4 : width * 4 / 5);
		this.setHeight(centralTerm ? height * 5 / 4 : height * 4 / 5);
		publish();
	}

	public boolean contains(int x, int y) {
		return (x >= (this.x - width / 2)) && (y >= (this.y - height / 2)) &&
			(x <= (this.x - width / 2 + width)) && (y <= (this.y - height / 2
			+ height));
	}
}
