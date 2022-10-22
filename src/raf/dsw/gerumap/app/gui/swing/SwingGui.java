package raf.dsw.gerumap.app.gui.swing;

import raf.dsw.gerumap.app.core.GUI;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGui implements GUI {

	@Override
	public void run() {
		MainFrame mainFrame = MainFrame.getInstance();
		mainFrame.init();
		mainFrame.setVisible(true);
	}
}
