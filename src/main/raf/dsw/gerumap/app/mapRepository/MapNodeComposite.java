package raf.dsw.gerumap.app.mapRepository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public abstract class MapNodeComposite extends MapNode {
	protected List<MapNode> children = new ArrayList<>();

	public abstract void removeChild(MapNode child);
	public abstract void addChild(MapNode child);
}
