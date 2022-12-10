package raf.dsw.gerumap.app.gui.swing.view;

import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.IPublisherImpl;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ProjectViewManager extends IPublisherImpl implements ISubscriber {
    private static ProjectViewManager instance;
    private Component projectView;
    private JPanel empty;
    private HashMap<Project, ProjectView> map = new HashMap<>();

    private ProjectViewManager() {
        AppCore.getInstance().getMapRepository().getProjectExplorer().addSubscriber(this);
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

    public void removeProject(Project p) {
        map.remove(p);
        if (map.isEmpty())
            projectView = empty;
        publish();
    }

    @Override
    public void update(IPublisher publisher) {
        if (publisher instanceof ProjectExplorer) {
            Project toRemove = null;
            for (Project p : map.keySet()) {
                if (!((ProjectExplorer) publisher).getChildren().contains(p)) {
                    toRemove = p;
                }
            }
            removeProject(toRemove);
        }
    }
}
