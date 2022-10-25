package raf.dsw.gerumap.app.mapRepository;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNode {
	protected String name;
	protected MapNode parent;
}
