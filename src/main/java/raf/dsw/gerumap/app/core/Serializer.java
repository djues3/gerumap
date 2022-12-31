package raf.dsw.gerumap.app.core;

import raf.dsw.gerumap.app.mapRepository.model.Project;

import java.io.File;

public interface Serializer {

	Project loadProject(File file);
	void saveProject(Project node);
}
