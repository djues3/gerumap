package raf.dsw.gerumap.app.mapRepository.model.elements;

import java.util.Objects;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.mapRepository.model.Element;

@Getter
@Setter
@ToString
public class Link extends Element {

	private Term from;
	private Term to;

	public Link() {
		name = "Link" + new Random().nextInt(100000);
	}

	public Link(Term from, Term to) {
		this();
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Link link = (Link) o;
		return (Objects.equals(from, link.from) && Objects.equals(to, link.to)) ||
			(Objects.equals(from, link.to) && Objects.equals(to, link.from));
	}

	public Term getOtherTerm(Term term) {
		if (term == null) {
			return null;
		}
		if (term.equals(from)) {
			return to;
		} else if (term.equals(to)) {
			return from;
		} else {
			return null;
		}
	}
}