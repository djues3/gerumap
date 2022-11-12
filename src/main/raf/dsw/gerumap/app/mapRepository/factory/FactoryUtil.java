package raf.dsw.gerumap.app.mapRepository.factory;

import raf.dsw.gerumap.app.mapRepository.factory.factories.ElementFactory;
import raf.dsw.gerumap.app.mapRepository.factory.factories.MindMapFactory;
import raf.dsw.gerumap.app.mapRepository.factory.factories.ProjectFactory;

public class FactoryUtil {
	public static NodeFactory getFactory(String parentType) {
		return switch (parentType) {
			case "ProjectExplorer" -> new ProjectFactory();
			case "Project" -> new MindMapFactory();
			case "MindMap" -> new ElementFactory();
			default -> null;
		};
	}
}
