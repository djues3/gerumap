package raf.dsw.gerumap.app.gui.swing.tree.controller;

import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getMapNode().getName());
        System.out.println("getPath: "+e.getPath());
    }
}


