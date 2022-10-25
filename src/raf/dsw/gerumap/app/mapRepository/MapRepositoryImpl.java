package raf.dsw.gerumap.app.mapRepository;

import raf.dsw.gerumap.app.core.MapRepository;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

public class MapRepositoryImpl implements MapRepository {

	@Override
	public void init() {
		ProjectExplorer root = new ProjectExplorer();
	}
}
