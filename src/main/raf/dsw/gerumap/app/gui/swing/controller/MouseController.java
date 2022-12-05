package raf.dsw.gerumap.app.gui.swing.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
@NoArgsConstructor
public class MouseController extends MouseAdapter {
	private MindMapView view;
	private final Component projectView = MainFrame.getInstance().getProjectView();
	@Override
	public void mousePressed(MouseEvent e) {
		ProjectView projectView = (ProjectView) this.projectView;
		projectView.mousePressed(e.getX(), e.getY(), view.getMindMap());
	}

}
