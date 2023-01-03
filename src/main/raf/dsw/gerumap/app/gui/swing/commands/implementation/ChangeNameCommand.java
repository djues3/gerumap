package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

import java.util.Map;

public class ChangeNameCommand extends AbstractCommand {
    MapTreeItem target;
    String before, after;

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
