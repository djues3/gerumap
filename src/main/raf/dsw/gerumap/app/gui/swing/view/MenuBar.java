package raf.dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {


	public MenuBar() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getAuthorAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
		this.add(fileMenu);
		this.add(helpMenu);

	}
}