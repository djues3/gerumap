package raf.dsw.gerumap.app.mapRepository.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MindMap extends MapNodeComposite {

	private boolean template;

	public MindMap(MapNode parent) {
		this.parent = parent;
	}

	public static boolean isCyclic(MindMap map, Term from, Term to) {
		List<MapNode> nodes = new ArrayList<>(map.getChildren());
		Link l = new Link(from, to);
		nodes.add(l);
		from.getLinks().add(l);
		to.getLinks().add(l);
		MindMap clone = new MindMap();
		clone.setChildren(nodes);
		if (isCyclic(clone)) {
			from.getLinks().remove(l);
			to.getLinks().remove(l);
			nodes.remove(l);
			return true;
		} else {
			from.getLinks().remove(l);
			to.getLinks().remove(l);
			nodes.remove(l);
			return false;
		}
	}

	public static boolean isCyclic(MindMap map) {
		List<MapNode> nodes = new ArrayList<>(map.getChildren());
		Deque<Term> stack = new ArrayDeque<>();
		Set<Term> seen = new HashSet<>();
		Set<Link> seenLinks = new HashSet<>();

		for (MapNode next : nodes) {
			if (next instanceof Term) {
				if (seen.contains(next)) {
					continue;
				}
				stack.add((Term) next);
				while (!stack.isEmpty()) {
					Term current = stack.pop();
					seen.add(current);
					for (Link link : current.getLinks()) {
						if (seenLinks.contains(link)) {
							continue;
						} else {
							seenLinks.add(link);
						}
						Term other = link.getOtherTerm(current);
						if (seen.contains(other)) {
							return true;
						} else {
							stack.add(other);
							seen.add(other);
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void removeChild(MapNode child) {
		if (!(child instanceof Element)) {
			AppCore.getInstance().getLogger().log(new Exception("Child is not an element"));
			System.out.println(child);
		}
		this.getChildren().remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if (!(child instanceof Element)) {
			System.out.println(child);
			throw new RuntimeException("");
		}
		this.children.add(child);
//		AppCore.getInstance().getMapRepository().getCommandManager().addCommand(new AddTreeChildCommand(this, child));

		publish();
	}

	public Term getTermAt(int x, int y) {
		for (MapNode child : children) {
			if (child instanceof Term term) {
				if (term.contains(x, y)) {
					return term;
				}
			}
		}
		return null;
	}
}
