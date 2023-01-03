package raf.dsw.gerumap.app.mapRepository.model;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
		if (!(child instanceof MindMap)) {
			throw new RuntimeException("");
		}
		this.getChildren().remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if (!(child instanceof MindMap)) {
			throw new RuntimeException("");
		}
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
