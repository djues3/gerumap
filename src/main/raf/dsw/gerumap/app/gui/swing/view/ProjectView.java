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
import java.awt.*;
import java.util.*;
import java.util.jar.JarEntry;

@Getter
@Setter
public class ProjectView extends JTabbedPane implements ISubscriber {
    private Project project;

    private JLabel label;
    private HashMap<MindMap, MindMapView> map = new HashMap<>();

    private void addMindMapView(MindMap m) {
        if (!map.containsKey(m)) {
            map.put(m, new MindMapView(m));
            m.addSubscriber(this);
        }
    }

    private void removeMindMapView(MindMap m) {
        if (map.containsKey(m)) {
            map.remove(m);
            m.removeSubscriber(this);
        }
    }

    public ProjectView(Project p) {
        project = p;
        project.addSubscriber(this);
        for (MapNode x : p.getChildren()) {
            ((MindMap)x).addSubscriber(this);
        }
        for (MapNode x : project.getChildren()) {
            addMindMapView((MindMap)x);
        }
    }


    @Override
    public void update(IPublisher publisher) {
        HashSet<MindMap> toRemove = new HashSet<>();

        for (MindMap m : map.keySet()) {
            if (!project.getChildren().contains(m)) {
                toRemove.add(m);
                for (int i = 0, size = getComponentCount() ; i < size; i++) {
                    if (((MindMapView)getComponentAt(i)).getMindMap() == m) {
                        this.removeTabAt(i);
                        break;
                    }
                }
            }
        }


        Iterator<MapNode> it = (project.getChildren()).iterator();
        while(it.hasNext()) {
            MindMap m = (MindMap)it.next();
            if (!map.containsKey(m)){
                addMindMapView(m);
                addTab(m.getName(), map.get(m));
            }
        }
        for (MindMap m : toRemove)
            removeMindMapView(m);

        for (int i = 0 , size = getComponentCount() ; i < size ; i++) {
            this.setTitleAt(i, ((MindMapView) getComponentAt(i)).getMindMap().getName());
        }

        System.out.println(map.keySet());
    }
}
