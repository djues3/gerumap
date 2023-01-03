package raf.dsw.gerumap.app.gui.swing.commands;

import javax.swing.*;
import java.util.Stack;

public class CommandManager {

    private Stack<AbstractCommand> undoStack = new Stack<>(),
                                   redoStack = new Stack<>();

    public void addCommand(AbstractCommand command) {
        undoStack.push(command);
        redoStack.clear();
        command.doCommand();
    }

    public void undoCommand() {
        if (undoStack.empty()) return;
        AbstractCommand command = undoStack.peek();
        command.undoCommand();
        undoStack.pop();
        redoStack.push(command);
    }

    public void redoCommand() {
        if (redoStack.empty()) return;
        AbstractCommand command = redoStack.peek();
        command.doCommand();
        redoStack.pop();
        undoStack.push(command);
    }
}
