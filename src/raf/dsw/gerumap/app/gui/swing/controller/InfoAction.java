package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGerumapAction{

	public InfoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadIcon("images/info.png"));
		putValue(NAME, "Info");
		putValue(SHORT_DESCRIPTION, "Info");
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		JDialog dialog = new JDialog();
		dialog.setTitle("Info");
		dialog.setSize(screenSize.width / 4, screenSize.height / 4);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooseStudent(panel);
		// Za spacing izmedju komponenti...

		dialog.setContentPane(panel);
		dialog.setLocationRelativeTo(MainFrame.getInstance());
		dialog.setVisible(true);
	}
	private void chooseStudent(JPanel panel) {
		JLabel ime;
		JLabel prezime;
		JLabel indeks;
		JLabel image;
		if(Math.random() < 0.5) {
			ime = new JLabel("Ime: David");
			ime.setAlignmentX(Component.CENTER_ALIGNMENT);
			prezime = new JLabel("Prezime: Djuretanovic");
			prezime.setAlignmentX(Component.CENTER_ALIGNMENT);
			indeks = new JLabel("Broj indeksa: RN86/22");
			indeks.setAlignmentX(Component.CENTER_ALIGNMENT);
			image = new JLabel(loadIcon("images/soon.png"));
			image.setAlignmentX(Component.CENTER_ALIGNMENT);
		} else {
			ime = new JLabel("Ime: Dusan");
			ime.setAlignmentX(Component.CENTER_ALIGNMENT);
			prezime = new JLabel("Prezime: Obradovic");
			prezime.setAlignmentX(Component.CENTER_ALIGNMENT);
			indeks = new JLabel("Broj indeksa: RN131/22");
			indeks.setAlignmentX(Component.CENTER_ALIGNMENT);
			image = new JLabel(loadIcon("images/no.png"));
			image.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		panel.add(Box.createVerticalStrut(20));
		panel.add(ime);
		panel.add(Box.createVerticalStrut(20));
		panel.add(prezime);
		panel.add(Box.createVerticalStrut(20));
		panel.add(indeks);
		panel.add(Box.createVerticalStrut(20));
		panel.add(image);
	}
}
