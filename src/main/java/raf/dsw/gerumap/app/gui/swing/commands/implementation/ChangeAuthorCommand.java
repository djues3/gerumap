package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.mapRepository.model.Project;

public class ChangeAuthorCommand extends AbstractCommand {

	Project target;
	String before, after;

	public ChangeAuthorCommand(Project target, String before, String after) {
		this.before = before;
		this.after = after;
		this.target = target;
	}

	@Override
	public void doCommand() {
		target.setAuthor(after);
	}

	@Override
	public void undoCommand() {
		target.setAuthor(before);
	}
}
