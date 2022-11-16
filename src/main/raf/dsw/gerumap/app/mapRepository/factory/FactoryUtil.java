package raf.dsw.gerumap.app.mapRepository.factory;

import raf.dsw.gerumap.app.mapRepository.factory.factories.ElementFactory;
import raf.dsw.gerumap.app.mapRepository.factory.factories.MindMapFactory;
import raf.dsw.gerumap.app.mapRepository.factory.factories.ProjectFactory;

public class FactoryUtil {
	private static final ProjectFactory projectFactory = new ProjectFactory();
	private static final MindMapFactory mindMapFactory = new MindMapFactory();
	private static final ElementFactory elementFactory = new ElementFactory();

	public static NodeFactory getFactory(String parentType) {
		return switch (parentType) {
			case "ProjectExplorer" -> projectFactory;
			case "Project" -> mindMapFactory;
			case "MindMap" -> elementFactory;
			default -> null;
		};
	}
}
