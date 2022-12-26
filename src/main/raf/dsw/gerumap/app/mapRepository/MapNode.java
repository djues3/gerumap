package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;

@Getter
@Setter
@ToString
public abstract class MapNode extends IPublisherImpl {

	protected String name;

	protected String type;
	@ToString.Exclude
	protected transient MapNode parent;

	public void setName(String name) {
		this.name = name;
		publish();
	}

	public MapNode() {
		type = getClass().getSimpleName();
	}
}
