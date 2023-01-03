package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

import javax.swing.*;

public class RemoveTreeChildCommand extends AbstractCommand {

    private MapTreeItem parent;
    private MapTreeItem child;


    public RemoveTreeChildCommand(MapTreeItem parent, MapTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        if(child == null ||  parent==null) return;
        child.removeFromParent();
        ((MapNodeComposite)(parent.getMapNode())).removeChild(child.getMapNode());
        MapTreeView treeView = (MainFrame.getInstance().getMapTree()).getMapTreeView();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void undoCommand() {
        if(child == null ||  parent==null) return;
        parent.add(child);
        ((MapNodeComposite) parent.getMapNode()).addChild(child.getMapNode());
        MapTreeView treeView = (MainFrame.getInstance().getMapTree()).getMapTreeView();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
