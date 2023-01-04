package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

public class ChangeNameCommand extends AbstractCommand {

	private final MapTreeItem target;
	private final String before;
	private final String after;

	public ChangeNameCommand(MapTreeItem target, String before, String after) {
		this.before = before;
		this.after = after;
		this.target = target;
	}

	@Override
	public void doCommand() {
		target.setName(after);
	}

	@Override
	public void undoCommand() {
		target.setName(before);
	}
}
