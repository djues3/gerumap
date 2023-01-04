package raf.dsw.gerumap.app.mapRepository.model.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.mapRepository.model.Element;

@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Term term = (Term) o;

		if (color != term.color) {
			return false;
		}
		if (x != term.x) {
			return false;
		}
		if (y != term.y) {
			return false;
		}
		if (width != term.width) {
			return false;
		}
		if (height != term.height) {
			return false;
		}
		if (centralTerm != term.centralTerm) {
			return false;
		}
		if (!name.equals(term.name)) {
			return false;
		}
		return Objects.equals(text, term.text);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + color;
		result = 31 * result + x;
		result = 31 * result + y;
		result = 31 * result + width;
		result = 31 * result + height;
		result = 31 * result + (centralTerm ? 1 : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}
}
