package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			try {
				String ext = getFileExtension(file.getAbsolutePath());
				if(ext.equals(""))
					ImageIO.write(image, "png", jfc.getSelectedFile());
				else
					ImageIO.write(image, ext, jfc.getSelectedFile());
			} catch (Exception ex) {
				AppCore.getInstance().getLogger().log(ex);
			}
		}
	}
	public static String getFileExtension(String fullName) {
		checkNotNull(fullName);
		String fileName = new File(fullName).getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	private static void checkNotNull(String fullName) {
		if(fullName == null)
			AppCore.getInstance().getMessageGenerator().generate(MessageType.FILENAME_CANNOT_BE_EMPTY, Message.Level.ERROR);
	}
}

