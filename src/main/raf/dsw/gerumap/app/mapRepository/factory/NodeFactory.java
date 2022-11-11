package raf.dsw.gerumap.app.mapRepository.factory;

import raf.dsw.gerumap.app.mapRepository.MapNode;

public interface NodeFactory {
	default MapNode getNode(MapNode parent) {
		MapNode node = createNode();
		node.setParent(parent);
		return node;
	}
	MapNode createNode();
}
