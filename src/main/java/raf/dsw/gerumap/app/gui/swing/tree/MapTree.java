package raf.dsw.gerumap.app.gui.swing.tree;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public interface MapTree {

	MapTreeView generateTree(ProjectExplorer projectExplorer);

	void addChild(MapTreeItem parent);

	void remove(MapTreeItem target);

	MapTreeView getMapTreeView();

	MapTreeItem getSelectedNode();


	void loadProject(Project p);

	void loadMindMap(MindMap m);
}
