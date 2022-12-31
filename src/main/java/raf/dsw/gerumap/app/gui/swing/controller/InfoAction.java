package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;

public class InfoAction extends AbstractGerumapAction {

	public InfoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadScaledIcon("/images/info.png"));
		putValue(NAME, "Info");
		putValue(SHORT_DESCRIPTION, "Info");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		JDialog dialog = new JDialog();
		dialog.setTitle("Info");
		dialog.setSize(screenSize.width / 3, screenSize.height / 3);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooseStudent(panel);
		dialog.setContentPane(panel);
		dialog.setLocationRelativeTo(MainFrame.getInstance());
		dialog.setVisible(true);
	}

	private void chooseStudent(JPanel panel) {
		JLabel name;
		JLabel surname;
		JLabel index;
		JLabel image;
		if (Math.random() < 0.5) {
			name = new JLabel("Ime: David");
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			surname = new JLabel("Prezime: Djuretanovic");
			surname.setAlignmentX(Component.CENTER_ALIGNMENT);
			index = new JLabel("Broj indeksa: RN86/22");
			index.setAlignmentX(Component.CENTER_ALIGNMENT);
			image = new JLabel(loadIcon("/images/soon.png"));
			image.setAlignmentX(Component.CENTER_ALIGNMENT);
		} else {
			name = new JLabel("Ime: Dusan");
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			surname = new JLabel("Prezime: Obradovic");
			surname.setAlignmentX(Component.CENTER_ALIGNMENT);
			index = new JLabel("Broj indeksa: RN131/22");
			index.setAlignmentX(Component.CENTER_ALIGNMENT);
			image = new JLabel(loadIcon("/images/no.png"));
			image.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		Component strut = Box.createVerticalStrut(20);
		panel.add(strut);
		panel.add(name);
		panel.add(strut);
		panel.add(surname);
		panel.add(strut);
		panel.add(index);
		panel.add(strut);
		panel.add(image);
	}
}
