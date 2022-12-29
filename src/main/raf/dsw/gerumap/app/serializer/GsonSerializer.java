package raf.dsw.gerumap.app.serializer;

import com.google.gson.*;
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
import java.lang.reflect.Type;

public class GsonSerializer implements Serializer {

	private final Gson gson;

	public GsonSerializer() {
		gson = new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.registerTypeAdapter(MapNode.class, new MapNodeDeserializer())
				.registerTypeAdapter(MapNode.class, new MapNodeSerializer())
				.create();

	}

	@Override
	public Project loadProject(File file) {
		try (FileReader reader = new FileReader(file)) {
			Project p = gson.fromJson(reader, Project.class);
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
			System.out.println(gson.toJson(project, Project.class));
			gson.toJson(project, writer);
		} catch (IOException e) {
						AppCore.getInstance().getMessageGenerator().generate(e.getMessage(), Message.Level.ERROR, e);
		}
	}
	private static class MapNodeSerializer implements JsonSerializer<MapNode> {
		@Override
		public JsonElement serialize(MapNode node, Type type, JsonSerializationContext context) {
			return switch (node.getType()) {
				case "Project" -> context.serialize(node, Project.class);
				case "MindMap" -> context.serialize(node, MindMap.class);
				case "Term" -> context.serialize(node, Term.class);
				case "Link" -> context.serialize(node, Link.class);
				default -> throw new JsonParseException("Unknown type: " + node.getType());
			};
		}
	}
	private static class MapNodeDeserializer implements JsonDeserializer<MapNode> {
		@Override
		public MapNode deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
			JsonObject object = element.getAsJsonObject();
			String typeString = object.get("type").getAsString();
			MapNode node = switch (object.get("type").getAsString()) {
				case "Project" -> context.deserialize(element, Project.class);
				case "MindMap" -> context.deserialize(element, MindMap.class);
				case "Term" -> context.deserialize(element, Term.class);
				case "Link" -> context.deserialize(element, Link.class);
				default -> throw new JsonParseException("Unknown type: " + typeString);
			};
			if(object.get("name").isJsonNull())
				node.setName(null);
			else
				node.setName(object.get("name").getAsString());
			return node;
		}
	}
}

