package raf.dsw.gerumap.app.gui.swing.controller;

import raf.dsw.gerumap.app.AppCore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGerumapAction {

    public UndoAction() {
        putValue(SMALL_ICON, loadScaledIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getMapRepository().getCommandManager().undoCommand();
    }
}
