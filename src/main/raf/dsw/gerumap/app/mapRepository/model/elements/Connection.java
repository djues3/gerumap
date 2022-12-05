package raf.dsw.gerumap.app.mapRepository.model.elements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.model.Element;

@Getter
@Setter
@NoArgsConstructor
public class Connection extends Element {
	private Term from;
	private Term to;
}
