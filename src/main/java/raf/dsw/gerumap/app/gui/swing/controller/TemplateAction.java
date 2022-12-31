package raf.dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;

public class TemplateAction extends AbstractGerumapAction {

	public TemplateAction() {
		putValue(NAME, "Template");
		putValue(SHORT_DESCRIPTION, "Template");
		putValue(SMALL_ICON, loadScaledIcon("/images/template.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.getInstance().getMapTree().getSelectedNode()
			.getMapNode() instanceof MindMap map) {
			map.setTemplate(!map.isTemplate());
		}
	}
}
