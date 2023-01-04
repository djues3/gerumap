package raf.dsw.gerumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.core.Serializer;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;
import raf.dsw.gerumap.app.messageGenerator.Message;

public class GsonSerializer implements Serializer {

	private final Gson gson;

	public GsonSerializer() {
		RuntimeTypeAdapterFactory<MapNode> typeAdapter = RuntimeTypeAdapterFactory.of(MapNode.class,
				"type")
			.recognizeSubtypes()
			.registerSubtype(Term.class)
			.registerSubtype(Link.class)
			.registerSubtype(MindMap.class)
			.registerSubtype(Project.class);
		gson = new GsonBuilder()
			.setPrettyPrinting()
			.serializeNulls()
			.registerTypeAdapterFactory(typeAdapter)
			.create();
	}

	@Override
	public Project loadProject(File file) {
		try (FileReader reader = new FileReader(file)) {
			Project p = gson.fromJson(reader, Project.class);
			for (MapNode node : p.getChildren()) {
				if (node instanceof MindMap map) {
					map.setParent(p);
					cleanUp(map);
				}
			}
			p.setFile(file);
			return p;
		} catch (IOException e) {
			AppCore.getInstance().getMessageGenerator()
				.generate(e.getMessage(), Message.Level.ERROR, e);
			return null;
		}
	}

	@Override
	public void saveProject(Project project) {
		try (FileWriter writer = new FileWriter(project.getFile())) {
			gson.toJson(project, Project.class, writer);
		} catch (IOException e) {
			AppCore.getInstance().getMessageGenerator()
				.generate(e.getMessage(), Message.Level.ERROR, e);
		}
	}

	@Override
	public MindMap loadMindMap(File file) {
		MindMap map;
		try (FileReader reader = new FileReader(file)) {
			map = gson.fromJson(reader, MindMap.class);
			cleanUp(map);
			System.out.println(map);
		} catch (IOException e) {
			AppCore.getInstance().getMessageGenerator()
				.generate(e.getMessage(), Message.Level.ERROR, e);
			return null;
		}
		return map;
	}

	@Override
	public void saveMindMap(MindMap node, File file) {
		try (FileWriter writer = new FileWriter(file)) {
			gson.toJson(node, MindMap.class, writer);
		} catch (IOException e) {
			AppCore.getInstance().getMessageGenerator()
				.generate(e.getMessage(), Message.Level.ERROR, e);
		}
	}

	/**
	 * Cleans up mind maps by adding all missing references lost during serialization. <p>Sets
	 * {@code getFrom()} and {@code getTo()}  of a {@code  Link} to the correct {@code  Terms}.
	 *  {@code Term trueFrom = ...} and {@code Term trueTo = ...} work because a {@code Term} must exist before a
	 *  {@code Link} can be created, and as such the {@code Term} is at a lower index in {@code map.getChildren()},
	 *  and gets returned by {@code ArrayList.indexOf()}. </p>
	 * @param map MindMap to clean up
	 */
	private void cleanUp(MindMap map) {
		List<MapNode> nodes = map.getChildren();
		for (MapNode node : nodes) {
			node.setParent(map);
			if (node instanceof Link l) {
				Term from = l.getFrom();
				Term to = l.getTo();
				Term trueFrom = (Term) nodes.get(nodes.indexOf(from));
				Term trueTo = (Term) nodes.get(nodes.indexOf(to));
				l.setFrom(trueFrom);
				l.setTo(trueTo);
				trueFrom.getLinks().add(l);
				trueTo.getLinks().add(l);
			}
		}
	}
}


