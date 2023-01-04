package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.app.observer.IPublisherImpl;

@Getter
@Setter
@ToString
public abstract class MapNode extends IPublisherImpl {

	protected String name;
	protected MapNode parent;

	public void setName(String name) {
		this.name = name;
		publish();
	}
}
