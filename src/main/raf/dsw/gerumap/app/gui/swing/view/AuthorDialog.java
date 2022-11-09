package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


@Getter
@Setter
public class AuthorDialog extends JDialog implements ISubscriber {

	private JLabel label;
	private JTextField field;
	private JButton button;
	private Project project;

	public AuthorDialog(Project project) {
		super(MainFrame.getInstance(), "Author", true);
		this.project = project;
		init();
	}

	private void init() {
		initWindow();
		initComponents();
		initListeners();
	}

	private void initListeners() {
		ActionListener listener = e -> {
			if(!(field.getText().equals("") || field.getText() == null))
			{
				project.setAuthor(field.getText());
				setVisible(false);
			} else {
				setVisible(false);
			}
		};
		button.addActionListener(listener);
		field.addActionListener(listener);
	}

	private void initComponents() {
		label = new JLabel("Autor: ");
		field = new JTextField(20);
		field.setText(project.getAuthor());
		button = new JButton("Postavi");
		rootPane.setLayout(new BoxLayout(rootPane, BoxLayout.Y_AXIS));
		rootPane.add(Box.createVerticalStrut(50));
		rootPane.add(label);
		rootPane.add(Box.createVerticalStrut(20));
		rootPane.add(field);
		rootPane.add(Box.createVerticalStrut(20));
		rootPane.add(button);
		rootPane.add(Box.createVerticalStrut(50));
	}

	private void initWindow() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		this.setSize(screenSize.width / 4, screenSize.height / 4);
		this.setLocationRelativeTo(MainFrame.getInstance());
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

	@Override
	public void update(IPublisher publisher) {
		if(publisher instanceof Project) {
			label.setText(project.getAuthor());
		}
	}
}
