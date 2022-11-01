package raf.dsw.gerumap.app.gui.swing.tree;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public interface MapTree {

    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent);
    MapTreeItem getSelectedNode();

}
