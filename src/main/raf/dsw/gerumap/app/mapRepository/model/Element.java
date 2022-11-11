package raf.dsw.gerumap.app.mapRepository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
@Getter
@Setter
@NoArgsConstructor
public class Element extends MapNode {

	public Element(MapNode parent) {
		this.parent = parent;
	}
}
