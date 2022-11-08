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
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ProjectView extends JTabbedPane implements ISubscriber {
    private Project project;

    private JTabbedPane tabbedPane;

    private JLabel label;

    public ProjectView() {
        init();
        for (MapNode x : AppCore.getInstance().getMapRepository().getProjectExplorer().getChildren()) {
            if (x instanceof Project) {
                x.addSubscriber(this);
            }
        }
    }

    private void init() {
        initComponents();
    }

    private void initComponents() {
        if (project == null) {
            return;
        }
        label = new JLabel("Projekat: " + project.getName());
        this.add(label);
        tabbedPane = new JTabbedPane();
        this.add(tabbedPane);
    }

    @Override
    public void update(IPublisher publisher) {
        init();
        this.removeAll();
        if (project == null) return;
        List<MindMap> mindMaps = new LinkedList<>();
        for (MapNode x : project.getChildren()) {
            mindMaps.add((MindMap)x);
        }
        for (MindMap x : mindMaps) {
            this.add(x.getName(), new JPanel());
        }

    }

    public void setProject(Project project) {
        this.removeAll();
        this.project = project;
        project.addSubscriber(this);
        List<MindMap> mindMaps = new LinkedList<>();
        for (MapNode x : project.getChildren()) {
            mindMaps.add((MindMap)x);
        }
        for (MindMap x : mindMaps) {
            this.add(x.getName(), new JPanel());
        }
    }
}
