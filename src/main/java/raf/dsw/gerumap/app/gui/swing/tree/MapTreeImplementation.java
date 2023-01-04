package raf.dsw.gerumap.app.gui.swing.tree;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.AddTreeChildCommand;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.RemoveTreeChildCommand;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeModel;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectViewManager;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.factory.FactoryUtil;
import raf.dsw.gerumap.app.mapRepository.factory.NodeFactory;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageType;

@Getter
@Setter
public class MapTreeImplementation implements MapTree {

	private MapTreeView treeView;
	private MapTreeModel treeModel;

	@Override
	public MapTreeView generateTree(ProjectExplorer projectExplorer) {
		MapTreeItem root = new MapTreeItem(projectExplorer);
		treeModel = new MapTreeModel(root);
		treeView = new MapTreeView(treeModel);
		return treeView;
	}

	@Override
	public void addChild(MapTreeItem parent) {

        if (!(parent.getMapNode() instanceof MapNodeComposite)
            || parent.getMapNode() instanceof MindMap) {
            return;
        }

		MapNode child = createChild(parent.getMapNode());
		AddTreeChildCommand command = new AddTreeChildCommand(parent, new MapTreeItem(child));
		AppCore.getInstance().getMapRepository().getCommandManager().addCommand(command);
	}

	@Override
	public void remove(MapTreeItem target) {
		try {
			MapNode parent = target.getMapNode().getParent();
			if (parent == null && target.getMapNode() instanceof ProjectExplorer) {
				AppCore.getInstance().getMessageGenerator()
					.generate(MessageType.PROJECT_EXPLORER_CANNOT_BE_REMOVED, Message.Level.ERROR);
				return;
			}
            if (!(parent instanceof MapNodeComposite)) {
                return;
            }
			RemoveTreeChildCommand command = new RemoveTreeChildCommand(
				(MapTreeItem) target.getParent(), target);
			AppCore.getInstance().getMapRepository().getCommandManager().addCommand(command);
		} catch (NullPointerException e) {
			AppCore.getInstance().getMessageGenerator()
				.generate(MessageType.NODE_CANNOT_BE_REMOVED, Message.Level.ERROR, e);
		}
	}

	@Override
	public MapTreeView getMapTreeView() {
		return treeView;
	}

	@Override
	public MapTreeItem getSelectedNode() {
		return (MapTreeItem) treeView.getLastSelectedPathComponent();
	}

	@Override
	public void loadProject(Project node) {
		MapTreeItem root = treeModel.getRoot();
		MapTreeItem mti = new MapTreeItem(node);
		for (MapNode child : node.getChildren()) {
			MapTreeItem childItem = new MapTreeItem(child);
			mti.add(childItem);
			child.setParent(node);
		}
		root.add(mti);
		treeView.expandPath(new TreePath(root.getPath()));
		SwingUtilities.updateComponentTreeUI(treeView);
		ProjectViewManager pvm = MainFrame.getInstance().getPvm();
		pvm.setProjectView(node);
	}

	private MapNode createChild(MapNode parent) {
//        return FactoryUtil.getFactory(parent.getClass().getSimpleName()).getNode(parent);
		String className = parent.getClass().getSimpleName();
		NodeFactory factory = FactoryUtil.getFactory(className);
		return factory.getNode(parent);
	}

}
