package raf.dsw.gerumap.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.controller.state.DeleteStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.EditStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.LinkStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.MoveStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.SelectionStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.TermStateAction;
import raf.dsw.gerumap.app.gui.swing.controller.state.ZoomStateAction;

@Getter
@Setter
public class ActionManager {

	private ExitAction exitAction;
	private NewAction newAction;
	private DeleteAction deleteAction;
	private AuthorAction authorAction;
	private InfoAction infoAction;
	private EditStateAction editStateAction;
	private SelectionStateAction selectionStateAction;
	private TermStateAction termStateAction;
	private DeleteStateAction deleteStateAction;
	private LinkStateAction linkStateAction;
	private MoveStateAction moveStateAction;
	private ZoomStateAction zoomStateAction;
	private SaveAction saveAction;
	private SaveAsAction saveAsAction;
	private OpenAction openAction;
	private ExportAction exportAction;
	private TemplateAction templateAction;
	private CentralTermAction centralTermAction;

	public ActionManager() {
		initActions();
	}

	private void initActions() {
		exitAction = new ExitAction();
		newAction = new NewAction();
		infoAction = new InfoAction();
		authorAction = new AuthorAction();
		deleteAction = new DeleteAction();
		editStateAction = new EditStateAction();
		selectionStateAction = new SelectionStateAction();
		termStateAction = new TermStateAction();
		deleteStateAction = new DeleteStateAction();
		linkStateAction = new LinkStateAction();
		moveStateAction = new MoveStateAction();
		zoomStateAction = new ZoomStateAction();
		saveAction = new SaveAction();
		saveAsAction = new SaveAsAction();
		openAction = new OpenAction();
		exportAction = new ExportAction();
		templateAction = new TemplateAction();
		centralTermAction = new CentralTermAction();
	}
}
