package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.commands.implementation.ChangeAuthorCommand;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.observer.IPublisher;
import raf.dsw.gerumap.app.observer.ISubscriber;

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
			if (!(field.getText().equals("") || field.getText() == null)) {
				ChangeAuthorCommand command = new ChangeAuthorCommand(project, project.getAuthor(),
					field.getText());
				MainFrame.getInstance().getCommandManager().addCommand(command);
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
		if (publisher instanceof Project) {
			label.setText(project.getAuthor());
		}
	}
}
