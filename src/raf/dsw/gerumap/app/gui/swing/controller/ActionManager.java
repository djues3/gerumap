package raf.dsw.gerumap.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

	private ExitAction exitAction;
	private NewProjectAction newProjectAction;

	private InfoAction infoAction;
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		exitAction = new ExitAction();
		newProjectAction = new NewProjectAction();
		infoAction = new InfoAction();
	}

}