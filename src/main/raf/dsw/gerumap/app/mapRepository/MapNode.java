package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;

@Getter
@Setter
public abstract class MapNode extends IPublisherImpl {

	public void setName(String name) {
		this.name = name;
		publish();
	}

	protected String name;
	protected MapNode parent;
}
