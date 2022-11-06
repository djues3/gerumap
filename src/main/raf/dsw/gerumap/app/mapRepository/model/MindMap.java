package raf.dsw.gerumap.app.mapRepository.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

@Getter
@Setter
public class MindMap extends MapNodeComposite {

	private boolean template;

	@Override
	public void addChild(MapNode child) {
		if(!(child instanceof Element))
			throw new RuntimeException("");
		this.children.add(child);
	}
}
