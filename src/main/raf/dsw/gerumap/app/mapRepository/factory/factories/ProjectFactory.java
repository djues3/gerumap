package raf.dsw.gerumap.app.mapRepository.factory.factories;

import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.factory.NodeFactory;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import java.util.Random;

public class ProjectFactory implements NodeFactory {
	@Override
	public MapNode createNode() {
		Project project = new Project();
		project.setName("Project " + new Random().nextInt(100));
		return project;
	}
}

