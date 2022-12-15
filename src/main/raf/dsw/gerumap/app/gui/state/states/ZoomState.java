package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;

import java.awt.*;

public class ZoomState extends State {
    private Float startX = 0f;
    private Float startY = 0f;
    private Float offsetX = 0f;

    private Float offsetY = 0f;
    private Float zoomPower;

    public Float getOffsetX() {
        return offsetX;
    }

    public Float getOffsetY() {
        return offsetY;
    }

    @Override
    public void mousePressed(int x, int y, MindMapView view) {
        startX = (float)x - offsetX;
        startY = (float)y - offsetY;
    }

    @Override
    public void mouseDragged(int x, int y, MindMapView view) {
        offsetX = (float)x - startX;
        offsetY = (float)y - startY;
        view.repaint();
    }
}