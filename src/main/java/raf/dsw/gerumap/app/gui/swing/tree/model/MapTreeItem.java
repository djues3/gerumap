package raf.dsw.gerumap.app.gui.swing.tree.model;

import javax.swing.tree.DefaultMutableTreeNode;
import raf.dsw.gerumap.app.mapRepository.MapNode;


public class MapTreeItem extends DefaultMutableTreeNode {

	private MapNode mapNode;

	public MapTreeItem(MapNode nodeModel) {
		this.mapNode = nodeModel;
	}

	@Override
	public String toString() {
		return mapNode.getName();
	}

	public void setName(String name) {
		this.mapNode.setName(name);
	}

	public MapNode getMapNode() {
		return mapNode;
	}
}
