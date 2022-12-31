package raf.dsw.gerumap.app.mapRepository.model;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.messageGenerator.Message;

public class ProjectExplorer extends MapNodeComposite {
	public ProjectExplorer() {
		this.setName("My Project Explorer");
		this.parent = null;
	}

	@Override
	public void removeChild(MapNode child) {
		if (!(child instanceof Project))
			AppCore.getInstance().getMessageGenerator().generate("Child is not a project", Message.Level.ERROR);
		this.children.remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if (!(child instanceof Project))
			throw new RuntimeException("");
		this.children.add(child);
		publish();
	}
}
