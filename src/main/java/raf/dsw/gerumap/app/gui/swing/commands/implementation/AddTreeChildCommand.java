package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;

public class AddTreeChildCommand extends AbstractCommand {


	private final MapTreeItem parent;
	private final MapTreeItem child;


	public AddTreeChildCommand(MapTreeItem parent, MapTreeItem child) {
		this.parent = parent;
		this.child = child;
	}

	@Override
	public void doCommand() {

        if (child == null || parent == null) {
            return;
        }
		parent.add(child);
		((MapNodeComposite) parent.getMapNode()).addChild(child.getMapNode());

	}

	@Override
	public void undoCommand() {
        if (child == null || parent == null) {
            return;
        }
		child.removeFromParent();
		((MapNodeComposite) (parent.getMapNode())).removeChild(child.getMapNode());
	}


}
