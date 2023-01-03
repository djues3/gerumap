package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditDialog extends JPanel {

	private JTextField textField;
	private JButton doneButton, colorButton;
	private Color color;

	public EditDialog() {
		color = null;
		doneButton = new JButton("Done");
		colorButton = new JButton("Color");
		this.setLayout(new GridLayout(3, 1, 20, 20));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		add(textField);
		add(doneButton);
		add(colorButton);
		colorButton.addActionListener(
			e -> color = JColorChooser.showDialog(this, "Pick a color", Color.BLACK));
		doneButton.addActionListener(e -> {
			Component component = (Component) e.getSource();
			JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
			dialog.dispose();
		});
	}

	public String getText() {
		return textField.getText();
	}

	public Color getColor() {
		return color;
	}
}
