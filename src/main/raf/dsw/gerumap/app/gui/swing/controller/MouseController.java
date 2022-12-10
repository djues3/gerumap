package raf.dsw.gerumap.app.gui.swing.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MouseController extends MouseAdapter {
	private MindMapView view;
	@Override
	public void mousePressed(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).mousePressed(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}
}
