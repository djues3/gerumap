package raf.dsw.gerumap.app.mapRepository;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode {
	protected List<MapNode> children = new ArrayList<>();

	public abstract void addChild(MapNode child);
}
