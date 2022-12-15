package raf.dsw.gerumap.app.gui.state.states;

import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.MindMapView;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;

import java.awt.*;

public class MoveState extends State {

    private Integer startX, startY;
    @Override
    public void mousePressed(int x, int y, MindMapView view) {
        startX = x;
        startY = y;
    }

    @Override
    public void mouseDragged(int x, int y, MindMapView view) {
        Component pv = MainFrame.getInstance().getPvm().getProjectView();
        if (!(pv instanceof ProjectView)) return;
        for (TermPainter curr : ((ProjectView) pv).getStateManager().getSelectionState().getSelectedTerms()) {
            curr.getTerm().setX(curr.getTerm().getX() + (x - startX));
            curr.getTerm().setY(curr.getTerm().getY() + (y - startY));
//            curr.getTerm().setY(y - startY);
//            curr.getTerm().setX(x - startX);
        }
        startY = y;
        startX = x;
        view.repaint();
    }
}
