package raf.dsw.gerumap.app.mapRepository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MindMap extends MapNodeComposite {

	private boolean template;

	public MindMap(MapNode parent) {
		this.parent = parent;
	}

	@Override
	public void removeChild(MapNode child) {
		if(!(child instanceof Element))
			throw new RuntimeException("");
		this.getChildren().remove(child);
	}

	@Override
	public void addChild(MapNode child) {
		if(!(child instanceof Element))
			throw new RuntimeException("");
		this.children.add(child);
	}
}
