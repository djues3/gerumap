package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.observer.IPublisherImpl;

@Getter
@Setter
public abstract class MapNode extends IPublisherImpl {
	protected String name;
	protected MapNode parent;
}
