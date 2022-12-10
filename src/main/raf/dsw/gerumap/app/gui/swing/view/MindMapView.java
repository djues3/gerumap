package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.gui.swing.controller.MouseController;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MindMapView extends JPanel implements ISubscriber {
    private MindMap mindMap;
    private List<Painter> painters = new ArrayList<>();

    public MindMapView(MindMap mindMap) {
        this.mindMap = mindMap;
        this.addMouseListener(new MouseController(this));
        this.mindMap.addSubscriber(this);
    }

    @Override
    public void update(IPublisher publisher) {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Painter painter : painters) {
            painter.draw(g);
        }
    }
    public void addPainter(Painter painter) {
        painters.add(painter);
    }
    public void removePainter(Painter painter) {
        painters.remove(painter);
    }
}
