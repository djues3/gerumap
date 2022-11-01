package raf.dsw.gerumap.app.mapRepository.model;

import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite {
	public ProjectExplorer() {
		this.setName("My Project Explorer");
		this.parent = null;
	}

	@Override
	public void addChild(MapNode child) {
		if (!(child instanceof Project))
			throw new RuntimeException("");
		this.children.add(child);
		publish();
	}
}
