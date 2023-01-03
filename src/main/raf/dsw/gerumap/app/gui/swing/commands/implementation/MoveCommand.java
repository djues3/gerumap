package raf.dsw.gerumap.app.gui.swing.commands.implementation;

import raf.dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.mapRepository.model.Element;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand {
    private List<Element> elements = new ArrayList<>();
    private int x_offset, y_offset;
    private MindMapView view;
    public MoveCommand(List<Element> elements, int x_offset, int y_offset, MindMapView view) {
        this.elements = elements;
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.view = view;
    }

    @Override



    public void doCommand() {
        for (Element e : elements) {
            if (e instanceof Term t) {
                t.setX(t.getX() + x_offset);
                t.setY(t.getY() + y_offset);
            }
        }
        view.repaint();
    }

    @Override
    public void undoCommand() {
        for (Element e : elements) {
            if (e instanceof Term t) {
                t.setX(t.getX() - x_offset);
                t.setY(t.getY() - y_offset);
            }
        }
        view.repaint();
    }
}
