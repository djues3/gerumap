package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;

public class AddLinkCommand extends AbstractCommand {

	private final Link link;
	private final MindMapView view;

	public AddLinkCommand(Link link, MindMapView view) {
		this.link = link;
		this.view = view;
	}

	@Override
	public void doCommand() {
		view.getMindMap().addChild(link);
		link.setParent(view.getMindMap());
		view.addPainter(new LinkPainter(link, view));
	}

	@Override
	public void undoCommand() {
		view.getMindMap().removeChild(link);
		link.setParent(null);
		view.removePainter(view.getPainterForLink(link));
	}
}
