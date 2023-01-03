package raf.dsw.gerumap.app.core;

import java.io.File;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;

public interface Serializer {

	Project loadProject(File file);

	void saveProject(Project node);
	MindMap loadMindMap(File file);
	void saveMindMap(MindMap node, File file);
}
