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
			AppCore.getInstance().getMessageGenerator()
				.generate(new Exception("Child is not an element"));
		}
		this.getChildren().remove(child);
		publish();
	}

	@Override
	public void addChild(MapNode child) {
		if (!(child instanceof Element)) {
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

	public void rearrange(Term centralTerm) {
		Rearranger.rearrange(this, centralTerm);
	}

	private static class Rearranger {

		static List<MapNode> nodes;
		static int noTerms;
		static Deque<Term> stack;
		static Set<Term> seen;
		static Set<Link> seenLinks;

		public static void rearrange(MindMap map, Term centralTerm) {
			nodes = map.children;
			stack = new ArrayDeque<>();
			seen = new HashSet<>();
			seenLinks = new HashSet<>();
			for (MapNode child : nodes) {
				if (child instanceof Term) {
					noTerms++;
				}
			}
			rearrange(centralTerm, 0d, 2 * Math.PI, 1);
			map.publish();
		}

		private static void rearrange(Term term, double leftBound, double rightBound,
			int iteration) {
			if (term.getLinks().isEmpty() || iteration > noTerms) {
				return;
			}
			double angle = (rightBound - leftBound) / term.getLinks().size();
			double currentAngle = leftBound;
			List<Link> links = term.getLinks();
			for (Link l : links) {
				currentAngle += angle;
				if (seenLinks.contains(l)) {
					continue;
				} else {
					seenLinks.add(l);
				}
				Term other = l.getOtherTerm(term);
				if (seen.contains(other)) {
					continue;
				} else {
					seen.add(other);
				}
				other.setX(term.getX() + (int) (Math.cos(currentAngle) * 200));
				other.setY(term.getY() + (int) (Math.sin(currentAngle) * 200));
				rearrange(other, currentAngle - angle, currentAngle, iteration + 1);
			}
		}
	}
}

