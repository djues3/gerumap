package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.observer.IPublisherImpl;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class MapNode extends IPublisherImpl {

	protected String name;

	@ToString.Exclude
	protected transient MapNode parent;

	public void setName(String name) {
		this.name = name;
		publish();
	}

	public MapNode(MapNode parent) {
		this.parent = parent;
	}
}
