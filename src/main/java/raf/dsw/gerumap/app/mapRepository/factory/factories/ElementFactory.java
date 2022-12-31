package raf.dsw.gerumap.app.mapRepository.factory.factories;

import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.factory.NodeFactory;
import raf.dsw.gerumap.app.mapRepository.model.Element;

import java.util.Random;

public class ElementFactory implements NodeFactory {

	@Override
	public MapNode createNode() {
		Element element = new Element();
		element.setName("Element " + new Random().nextInt(100));
		return element;
	}
}
