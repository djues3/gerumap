import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

public class MindMapTest {

	private final MindMap map = new MindMap();

	@Test
	public void testIsCycle() {
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
		assertTrue(MindMap.isCyclic(map, t1, t2));
	}
	@Test
	public void TestIsntCycle() {
		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Term t4 = new Term();
		Link l1 = new Link(t1, t2);
		Link l2 = new Link(t2, t3);
		Link l3 = new Link(t3, t4);
//		Link l4 = new Link(t4, t1);
		t1.getLinks().add(l1);
		t2.getLinks().add(l1);
		t2.getLinks().add(l2);
		t3.getLinks().add(l2);
		t3.getLinks().add(l3);
		t4.getLinks().add(l3);
//		t4.getLinks().add(l4);
//		t1.getLinks().add(l4);
		map.getChildren().add(t1);
		map.getChildren().add(t2);
		map.getChildren().add(t3);
		map.getChildren().add(t4);
		map.getChildren().add(l1);
		map.getChildren().add(l2);
		map.getChildren().add(l3);
//		map.getChildren().add(l4);
		assertFalse(MindMap.isCyclic(map, t1, t3));
	}
}
