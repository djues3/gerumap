package raf.dsw.gerumap.app.gui.swing.tree.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import raf.dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import raf.dsw.gerumap.app.gui.swing.tree.controller.MapTreeSelectionListener;

public class MapTreeView extends JTree {


	public MapTreeView(DefaultTreeModel defaultTreeModel) {
		setModel(defaultTreeModel);
		MapTreeCellRenderer renderer = new MapTreeCellRenderer();
		addTreeSelectionListener(new MapTreeSelectionListener());
		setCellEditor(new MapTreeCellEditor(this, renderer));
		setCellRenderer(renderer);
		setEditable(true);
	}
}
