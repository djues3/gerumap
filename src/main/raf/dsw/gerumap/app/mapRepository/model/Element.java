package raf.dsw.gerumap.app.mapRepository.model;

import raf.dsw.gerumap.app.mapRepository.MapNode;

public class Element extends MapNode {

	public Element(MapNode parent) {
		this.parent = parent;
	}
}
