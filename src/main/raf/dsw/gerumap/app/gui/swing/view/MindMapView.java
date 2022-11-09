package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;

import javax.swing.*;
@Getter
@Setter
public class MindMapView extends JPanel implements ISubscriber {
    private MindMap mindMap;

    public MindMapView(MindMap mindMap) {
        this.mindMap = mindMap;
    }

    @Override
    public void update(IPublisher publisher) {

    }
}
