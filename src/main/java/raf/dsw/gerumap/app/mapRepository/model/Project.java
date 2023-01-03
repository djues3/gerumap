package raf.dsw.gerumap.app.mapRepository.model;

import java.nio.file.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends MapNodeComposite {

	private Path filePath;
	private String author;

	public Project(MapNode parent) {
		this.parent = parent;
	}

	public void setAuthor(String author) {
		this.author = author;
		publish();
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
}
