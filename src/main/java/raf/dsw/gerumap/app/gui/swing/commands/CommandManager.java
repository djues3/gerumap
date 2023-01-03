package raf.dsw.gerumap.app.gui.swing.commands;

import java.util.Stack;
import javax.swing.SwingUtilities;
import raf.dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;

public class CommandManager {

	private final Stack<AbstractCommand> undoStack = new Stack<>();
	private final Stack<AbstractCommand> redoStack = new Stack<>();

	public void addCommand(AbstractCommand command) {
		undoStack.push(command);
		redoStack.clear();
		command.doCommand();
		MapTreeView treeView = (MainFrame.getInstance().getMapTree()).getMapTreeView();
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	public void undoCommand() {
		if (undoStack.empty()) {
			return;
		}
		AbstractCommand command = undoStack.peek();
		command.undoCommand();
		undoStack.pop();
		redoStack.push(command);
		MapTreeView treeView = (MainFrame.getInstance().getMapTree()).getMapTreeView();
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	public void redoCommand() {
		if (redoStack.empty()) {
			return;
		}
		AbstractCommand command = redoStack.peek();
		command.doCommand();
		redoStack.pop();
		undoStack.push(command);
		MapTreeView treeView = (MainFrame.getInstance().getMapTree()).getMapTreeView();
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
}
