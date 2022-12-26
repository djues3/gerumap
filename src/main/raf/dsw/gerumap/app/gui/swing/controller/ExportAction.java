package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class ExportAction extends AbstractGerumapAction {
	@Override
	public void actionPerformed(ActionEvent e) {
		Component c = ((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView();
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		c.printAll(g);
		g.dispose();
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
				ImageIO.write(image, "png", jfc.getSelectedFile());
			} catch (Exception ex) {
				AppCore.getInstance().getLogger().log(ex);
			}
		}


	}
}

