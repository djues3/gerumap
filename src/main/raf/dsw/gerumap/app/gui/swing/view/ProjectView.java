package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.core.ApplicationFramework;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.MapNodeComposite;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.*;
import java.util.*;

@Getter
@Setter
public class ProjectView extends JTabbedPane implements ISubscriber {
    private Project project;

    private JLabel label;

    public ProjectView() {
        for (MapNode x : AppCore.getInstance().getMapRepository().getProjectExplorer().getChildren()) {
            if (x instanceof Project) {
                x.addSubscriber(this);
            }
        }
    }


    @Override
    public void update(IPublisher publisher) {
        this.removeAll();
        if (project == null) return;
        List<MindMap> mindMaps = new LinkedList<>();
        for (MapNode x : project.getChildren()) {
            mindMaps.add((MindMap)x);
        }
        for (MindMap x : mindMaps) {
            this.add(x.getName(), new MindMapView(x));
        }
    }

    public void setProject(Project project) {
        if (project == this.project) return;
        this.removeAll();
        this.project = project;
        project.addSubscriber(this);
        for (MapNode x : project.getChildren()) {
            this.addTab(x.getName(), new MindMapView((MindMap) x));
        }
    }
}
