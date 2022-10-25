package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.observer.IPublisher;
import raf.dsw.gerumap.app.gui.swing.observer.ISubscriber;

import javax.swing.*;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {
    @Override
    public void update(IPublisher publisher) {

    }

    public ProjectView() {

    }

}
