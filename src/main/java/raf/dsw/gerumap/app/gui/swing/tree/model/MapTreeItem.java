package raf.dsw.gerumap.app.gui.swing.tree.model;

import raf.dsw.gerumap.app.mapRepository.MapNode;

import javax.swing.tree.DefaultMutableTreeNode;


public class MapTreeItem extends DefaultMutableTreeNode {

    private final MapNode mapNode;

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
