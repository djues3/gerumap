package raf.dsw.gerumap.app.mapRepository.model;

import lombok.*;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class MindMap extends MapNodeComposite {

	private boolean template;

	@Override
	public void removeChild(MapNode child) {
		if(!(child instanceof Element))
			AppCore.getInstance().getLogger().log(new Exception("Child is not an element"));
		this.getChildren().remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if(!(child instanceof Element))
			throw new RuntimeException("");
		this.children.add(child);
		publish();
	}

	public Term getTermAt(int x, int y) {
		for (MapNode child : children) {
			if (child instanceof Term term) {
				if (term.contains(x, y)) {
					return term;
				}
			}
		}
		return null;
	}
}
