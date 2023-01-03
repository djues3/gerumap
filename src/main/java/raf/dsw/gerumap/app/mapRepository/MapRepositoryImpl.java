package raf.dsw.gerumap.app.mapRepository;

import raf.dsw.gerumap.app.core.MapRepository;
import raf.dsw.gerumap.app.gui.swing.commands.CommandManager;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public class MapRepositoryImpl implements MapRepository {

	protected ProjectExplorer root;
	private CommandManager commandManager;

	@Override
	public void init() {
		root = new ProjectExplorer();
		commandManager = new CommandManager();
	}

	@Override
	public ProjectExplorer getProjectExplorer() {
		return root;
	}

	@Override
	public CommandManager getCommandManager() {
		return commandManager;
	}
}
