package raf.dsw.gerumap.app.mapRepository.model.elements;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.mapRepository.model.Element;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Link extends Element {

	private Term from;
	private Term to;

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
		if (term.equals(from)) {
			return to;
		} else if (term.equals(to)) {
			return from;
		} else {
			return null;
		}
	}
}