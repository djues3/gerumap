package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.*;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    private Project project;

    private JTabbedPane tabbedPane;
    private JLabel label;


    public ProjectView() {
        init();
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
    }
}
