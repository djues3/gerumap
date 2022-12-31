package raf.dsw.gerumap.app.mapRepository;

import raf.dsw.gerumap.app.core.MapRepository;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public class MapRepositoryImpl implements MapRepository {
	protected ProjectExplorer root;
	@Override
	public void init() {
		root = new ProjectExplorer();
	}

	@Override
	public ProjectExplorer getProjectExplorer() {
		return root;
	}
}
