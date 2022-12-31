package raf.dsw.gerumap.app.gui.swing.tree.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


	private Object clickedOn = null;
	private JTextField edit = null;

	public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
	}

	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected,
		boolean expanded,
		boolean leaf, int row) {
		clickedOn = value;
		edit = new JTextField(value.toString());
		edit.addActionListener(e -> ((MapTreeItem) value).getMapNode().setName(edit.getText()));
		return edit;
	}

	public boolean isCellEditable(EventObject event) {
		if (event instanceof MouseEvent) {
			return ((MouseEvent) event).getClickCount() == 3;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (!(clickedOn instanceof MapTreeItem clicked)) {
			return;
		}
		clicked.setName(e.getActionCommand());
	}


}
