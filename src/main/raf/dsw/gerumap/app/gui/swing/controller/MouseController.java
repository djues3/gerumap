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
import java.awt.event.MouseWheelEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MouseController extends MouseAdapter {
	private MindMapView view;
	@Override
	public void mousePressed(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mousePressed(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mouseDragged(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mouseClicked(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mouseReleased(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mouseMoved(e.getX(), e.getY(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("lmao");
		try {
			((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView().mouseWheelMoved(e.getX(), e.getY(), e.getWheelRotation(), view);
		} catch (Exception ex) {
			AppCore.getInstance().getLogger().log(ex);
		}
	}
}
