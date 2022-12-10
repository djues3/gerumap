package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

@Getter
@Setter
@NoArgsConstructor
public class LinkState extends State {
	private Link link;
	@Override
	public void mousePressed(int x, int y, MindMapView view) {
		Term term = view.getMindMap().getTermAt(x, y);
		if (term != null) {
			if (link == null) {
				link = new Link();
				link.setFrom(term);
			} else {
				link.setTo(term);
				view.getMindMap().addChild(link);
				view.addPainter(new LinkPainter(link));
				link = null;
			}
		}
	}
}

