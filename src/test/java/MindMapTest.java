import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class MindMapTest {


	@Test
	public void testIsCyclic4NewLink() {
		MindMap map = new MindMap();
		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Term t4 = new Term();
		Link l1 = new Link(t1, t2);
		Link l2 = new Link(t2, t3);
		Link l3 = new Link(t3, t4);
		Link l4 = new Link(t4, t1);
		t1.getLinks().add(l1);
		t2.getLinks().add(l1);
		t2.getLinks().add(l2);
		t3.getLinks().add(l2);
		t3.getLinks().add(l3);
		t4.getLinks().add(l3);
		t4.getLinks().add(l4);
		t1.getLinks().add(l4);
		map.getChildren().add(t1);
		map.getChildren().add(t2);
		map.getChildren().add(t3);
		map.getChildren().add(t4);
		map.getChildren().add(l1);
		map.getChildren().add(l2);
		map.getChildren().add(l3);
		map.getChildren().add(l4);
		assertTrue(MindMap.isCyclic(map, t1, t2), "Cyclic map should be detected");
	}

	@Test
	public void testIsNotCyclic3NewLink() {
		MindMap map = new MindMap();
		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Link l = new Link(t1, t2);
		t1.getLinks().add(l);
		t2.getLinks().add(l);
		map.getChildren().add(t1);
		map.getChildren().add(t2);
		map.getChildren().add(t3);
		map.getChildren().add(l);
		assertFalse(MindMap.isCyclic(map, t1, t3),
			"Non-cyclic map should not be detected as cyclic");
	}

	@Test
	public void testIsNotCyclic7NewLink() {
		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Term t4 = new Term();
		Term t5 = new Term();
		Term t6 = new Term();
		Term t7 = new Term();
		Link l1 = new Link(t1, t2);
		Link l2 = new Link(t1, t3);
		Link l3 = new Link(t2, t4);
		Link l4 = new Link(t3, t5);
		Link l5 = new Link(t5, t6);
//		Link l6 = new Link(t3, t7);
		t1.getLinks().add(l1);
		t1.getLinks().add(l2);
		t2.getLinks().add(l1);
		t2.getLinks().add(l3);
		t3.getLinks().add(l2);
		t3.getLinks().add(l4);
//		t3.getLinks().add(l6);
		t4.getLinks().add(l3);
		t5.getLinks().add(l4);
		t5.getLinks().add(l5);
		t6.getLinks().add(l5);
//		t7.getLinks().add(l6);
		MindMap map = new MindMap();
		map.getChildren().add(t1);
		map.getChildren().add(t2);
		map.getChildren().add(t3);
		map.getChildren().add(t4);
		map.getChildren().add(t5);
		map.getChildren().add(t6);
		map.getChildren().add(t7);
		map.getChildren().add(l1);
		map.getChildren().add(l2);
		map.getChildren().add(l3);
		map.getChildren().add(l4);
		map.getChildren().add(l5);
//		map.getChildren().add(l6);
		assertFalse(MindMap.isCyclic(map, t3, t7),
			"Non-cyclic map should not be detected as cyclic");

	}

	@Test
	public void testIsNotCyclic7() {
		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Term t4 = new Term();
		Term t5 = new Term();
		Term t6 = new Term();
		Term t7 = new Term();
		Link l1 = new Link(t1, t2);
		Link l2 = new Link(t1, t3);
		Link l3 = new Link(t2, t4);
		Link l4 = new Link(t3, t5);
		Link l5 = new Link(t5, t6);
		Link l6 = new Link(t3, t7);
		t1.getLinks().add(l1);
		t1.getLinks().add(l2);
		t2.getLinks().add(l1);
		t2.getLinks().add(l3);
		t3.getLinks().add(l2);
		t3.getLinks().add(l4);
		t3.getLinks().add(l6);
		t4.getLinks().add(l3);
		t5.getLinks().add(l4);
		t5.getLinks().add(l5);
		t6.getLinks().add(l5);
		t7.getLinks().add(l6);
		MindMap map = new MindMap();
		map.getChildren().add(t1);
		map.getChildren().add(t2);
		map.getChildren().add(t3);
		map.getChildren().add(t4);
		map.getChildren().add(t5);
		map.getChildren().add(t6);
		map.getChildren().add(t7);
		map.getChildren().add(l1);
		map.getChildren().add(l2);
		map.getChildren().add(l3);
		map.getChildren().add(l4);
		map.getChildren().add(l5);
		map.getChildren().add(l6);
		assertFalse(MindMap.isCyclic(map));
	}
}
