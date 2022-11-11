package raf.dsw.gerumap.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

	private ExitAction exitAction;
	private NewAction newAction;
	private DeleteAction deleteAction;
	private AuthorAction authorAction;
	private InfoAction infoAction;
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		exitAction = new ExitAction();
		newAction = new NewAction();
		infoAction = new InfoAction();
		authorAction = new AuthorAction();
		deleteAction = new DeleteAction();
	}

}
