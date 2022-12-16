package raf.dsw.gerumap.app.gui.swing.tree.controller;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.view.ProjectViewManager;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem) path.getLastPathComponent();
//        ProjectView projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance();
        if(treeItemSelected.getMapNode() instanceof Project) {

//            projectView.setProject((Project) treeItemSelected.getMapNode());
            projectViewManager.setProjectView((Project) treeItemSelected.getMapNode());
        }
        else {
            if (!(treeItemSelected.getMapNode() instanceof Project)) {
                MapNode current = treeItemSelected.getMapNode();
                while (!(current instanceof Project)) {
                    if (current == null)
                        break;
                    current = current.getParent();
                }
                if (current != null)
                    projectViewManager.setProjectView((Project) current);
//                    projectView.setProject((Project) current);
            }
        }
    }
}


