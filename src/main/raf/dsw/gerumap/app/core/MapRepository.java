package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.gui.swing.commands.CommandManager;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public interface MapRepository {
	void init();
	ProjectExplorer getProjectExplorer();
	CommandManager getCommandManager();
}
