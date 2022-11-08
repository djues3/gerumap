package raf.dsw.gerumap.app.gui.swing.tree.controller;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem) path.getLastPathComponent();
        if(treeItemSelected.getMapNode() instanceof Project) {
            System.out.println("Selektovan cvor: " + treeItemSelected.getMapNode().getName() +
                    " Autor je: " + ((Project) treeItemSelected.getMapNode()).getAuthor());
            MainFrame.getInstance().getProjectView().setProject((Project) treeItemSelected.getMapNode());
        }
        System.out.println("getPath: "+e.getPath());
    }
}


