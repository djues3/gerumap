package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.messageGenerator.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
public class ExportAction extends AbstractGerumapAction {

	public ExportAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		putValue(NAME, "Export");
		putValue(SMALL_ICON, loadScaledIcon("/images/export.png"));
		putValue(SHORT_DESCRIPTION, "Export");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component c = ((ProjectView) MainFrame.getInstance().getProjectView()).getMindMapView();
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		c.printAll(g);
		g.dispose();
		JFileChooser jfc = new JFileChooser();
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Network Graphics", "png");
		jfc.addChoosableFileFilter(filter);
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
					ImageIO.write(image, "png", jfc.getSelectedFile());
			} catch (Exception ex) {
				AppCore.getInstance().getMessageGenerator().generate("Error while exporting the map", Message.Level.ERROR, ex);
			}
		}
	}
}

