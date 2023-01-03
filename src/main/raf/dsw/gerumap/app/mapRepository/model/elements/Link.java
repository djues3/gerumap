package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.*;
import raf.dsw.gerumap.app.mapRepository.model.Element;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link extends Element {
	private Term from;
	private Term to;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Link link = (Link) o;
		return (Objects.equals(from, link.from) && Objects.equals(to, link.to)) ||
				(Objects.equals(from, link.to) && Objects.equals(to, link.from));
	}

}