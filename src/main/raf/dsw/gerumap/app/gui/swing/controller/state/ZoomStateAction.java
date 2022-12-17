package raf.dsw.gerumap.app.gui.swing.controller.state;

import raf.dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.gui.swing.view.ProjectView;

import java.awt.event.ActionEvent;

public class ZoomStateAction extends AbstractGerumapAction {

    public ZoomStateAction() {
        putValue(NAME, "Zoom");
        putValue(SHORT_DESCRIPTION, "Zoom");
        putValue(SMALL_ICON, loadScaledIcon("/images/zoomState.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ProjectView) MainFrame.getInstance().getProjectView()).startZoomState();
    }
}
