package raf.dsw.gerumap.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.swing.controller.state.*;

@Getter
@Setter
public class ActionManager {

	private ExitAction exitAction;
	private NewAction newAction;
	private DeleteAction deleteAction;
	private AuthorAction authorAction;
	private InfoAction infoAction;
	private EditStateAction editStateAction;
	private SelectStateAction selectStateAction;
	private TermStateAction termStateAction;
	private DeleteStateAction deleteStateAction;
	private LinkStateAction linkStateAction;

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
		selectStateAction = new SelectStateAction();
		termStateAction = new TermStateAction();
		deleteStateAction = new DeleteStateAction();
		linkStateAction = new LinkStateAction();
	}
}
