package raf.dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractGerumapAction extends AbstractAction {


	public Icon loadIcon(String fileName){

		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;

		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		else {
			System.err.println("Resource not found: " + fileName);
		}
		return icon;
	}
	public Icon loadScaledIcon(String fileName, int width, int height){
		return new ImageIcon(((ImageIcon) loadIcon(fileName)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	public Icon loadScaledIcon(String fileName) {
		return loadScaledIcon(fileName, 50, 50);
	}
}

