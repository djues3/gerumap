package raf.dsw.gerumap.app.gui.swing.view;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProjectViewManager extends IPublisherImpl {
    private static ProjectViewManager instance;
    private Component projectView;
    private JPanel empty;
    private HashMap<Project, ProjectView> map = new HashMap<Project, ProjectView>();

    private ProjectViewManager() {
        empty = new JPanel();
        projectView = empty;
    }

    public static ProjectViewManager getInstance() {
        if (instance == null)
            instance = new ProjectViewManager();
        return instance;
    }

    public Component getProjectView() {
        return projectView;
    }

    public void setProjectView(Project p) {
        addProject(p);
        projectView = map.get(p);
        publish();
    }

    private void addProject(Project p) {
        if (!map.containsKey(p))
            map.put(p, new ProjectView(p));
    }
}
