package raf.dsw.gerumap.app.gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.Element;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageType;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

@Getter
@Setter
public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void remove(MapTreeItem target) {
        try {
            MapNode parent = target.getMapNode().getParent();
            if (!(parent instanceof  MapNodeComposite))
                return;
            ((DefaultMutableTreeNode) target.getParent()).remove(target);
            ((MapNodeComposite) parent).removeChild(target.getMapNode());
            SwingUtilities.updateComponentTreeUI(treeView);
        } catch (NullPointerException e) {
            AppCore.getInstance().getMessageGenerator()
                    .generate(MessageType.NODE_CANNOT_BE_REMOVED, Message.Level.ERROR, e);
        }
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            {
            Project project =  new Project(parent);
            project.setName("Project " + new Random().nextInt(100));
//            project.addSubscriber(MainFrame.getInstance().getProjectView());
            return project;
        }
        if (parent instanceof Project) {
            MindMap map = new MindMap(parent);
            map.setName("MindMap " + new Random().nextInt(100));
            return map;
        }

        if (parent instanceof MindMap) {
            Element element = new Element(parent);
            element.setName("Element " + new Random().nextInt(100));
            return element;
        }

        return null;
    }

}
