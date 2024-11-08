package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.Image;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import raf.dsw.gerumap.app.AppCore;

public abstract class AbstractGerumapAction extends AbstractAction {


	public Icon loadIcon(String fileName) {

		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;

		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		} else {
			AppCore.getInstance().getMessageGenerator()
				.generate(new RuntimeException("Resource not found: " + fileName));
		}
		return icon;
	}

	public Icon loadScaledIcon(String fileName, int width, int height) {
		return new ImageIcon(((ImageIcon) loadIcon(fileName)).getImage()
			.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	public Icon loadScaledIcon(String fileName) {
		return loadScaledIcon(fileName, 50, 50);
	}
}

