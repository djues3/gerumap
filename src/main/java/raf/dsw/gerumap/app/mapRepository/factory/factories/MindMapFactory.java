package raf.dsw.gerumap.app.mapRepository.factory.factories;

import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.factory.NodeFactory;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;

import java.util.Random;

public class MindMapFactory implements NodeFactory {
	@Override
	public MapNode createNode() {
		MindMap mindMap = new MindMap();
		mindMap.setName("MindMap " + new Random().nextInt(100));
		return mindMap;
	}
}

