package raf.dsw.gerumap.app.mapRepository;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode {

	protected List<MapNode> children = new ArrayList<>();

	public abstract void removeChild(MapNode child);

	public abstract void addChild(MapNode child);
}
