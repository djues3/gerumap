package raf.dsw.gerumap.app.mapRepository.model;

import lombok.*;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Project extends MapNodeComposite {

	private transient File file;
	private transient boolean modified;

	public void setAuthor(String author) {
		this.author = author;
		publish();
	}

	private String author;

	public Project(MapNode parent) {
		this.parent = parent;
	}

	public void removeChild(MapNode child) {
		if (!(child instanceof MindMap))
			throw new RuntimeException("");
		this.getChildren().remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if(!(child instanceof MindMap))
			throw new RuntimeException("");
		this.children.add(child);
		publish();
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
}
