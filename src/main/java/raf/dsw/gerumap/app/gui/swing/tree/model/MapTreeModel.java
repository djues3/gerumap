package raf.dsw.gerumap.app.gui.swing.tree.model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import lombok.ToString;

@ToString
public class MapTreeModel extends DefaultTreeModel {

	public MapTreeModel(TreeNode root) {
		super(root);
	}

	@Override
	public MapTreeItem getRoot() {
		return (MapTreeItem) super.getRoot();
	}
}
