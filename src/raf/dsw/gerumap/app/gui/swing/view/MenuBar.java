package raf.dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {


	public MenuBar() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.add(MainFrame.getInstance().getActionManager().getExitAction());
		menu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

		this.add(menu);

	}
}
