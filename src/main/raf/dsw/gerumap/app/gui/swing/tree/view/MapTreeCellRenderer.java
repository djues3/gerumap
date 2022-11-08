package raf.dsw.gerumap.app.gui.swing.tree.view;

import lombok.NoArgsConstructor;
import raf.dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

@NoArgsConstructor
public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
            URL imageURL = null;

            if (((MapTreeItem)value).getMapNode() instanceof ProjectExplorer) {
                imageURL = getClass().getResource("/images/projectExplorer.png");
            }
            else if (((MapTreeItem)value).getMapNode() instanceof Project) {
                imageURL = getClass().getResource("/images/project.png");
            } else if (((MapTreeItem)value).getMapNode() instanceof MindMap) {
                imageURL = getClass().getResource("/images/mindMap.png");
            }

            Icon icon = null;
            if (imageURL != null)
                // Posto su slike 100x100, skalirane su na 20x20
                icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            setIcon(icon);

            return this;
        }

}


