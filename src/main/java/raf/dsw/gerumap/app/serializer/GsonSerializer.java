package raf.dsw.gerumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.core.Serializer;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;
import raf.dsw.gerumap.app.messageGenerator.Message;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

	private final Gson gson;

	public GsonSerializer() {
		RuntimeTypeAdapterFactory<MapNode> typeAdapter = RuntimeTypeAdapterFactory.of(MapNode.class, "type")
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
			Project p = (Project) gson.fromJson(reader, MapNode.class);
			p.setFile(file);
			System.out.println("p = " + p);
			return p;
		} catch (IOException e) {
						AppCore.getInstance().getMessageGenerator().generate(e.getMessage(), Message.Level.ERROR, e);
			return null;
		}
	}

	@Override
	public void saveProject(Project project) {
		try (FileWriter writer = new FileWriter(project.getFile())) {
			System.out.println(gson.toJson(project, MapNode.class));
			gson.toJson(project, writer);
		} catch (IOException e) {
						AppCore.getInstance().getMessageGenerator().generate(e.getMessage(), Message.Level.ERROR, e);
		}
	}
}

