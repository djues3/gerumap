package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractGerumapAction{
    public RedoAction() {
        putValue(SMALL_ICON, loadScaledIcon("/images/redo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getMapRepository().getCommandManager().redoCommand();
    }
}
