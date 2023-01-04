package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {


	public MenuBar() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
//		fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getAuthorAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getExportAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getTemplateAction());
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
		this.add(fileMenu);
		this.add(helpMenu);

	}
}
