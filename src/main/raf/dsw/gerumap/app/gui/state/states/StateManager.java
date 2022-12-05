package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.state.TermState;

@Getter
public class StateManager {
	private static final TermState termState = new TermState();
	private static final EditState editState = new EditState();
	private static final DeleteState deleteState = new DeleteState();
	private static final ConnectionState connectionState = new ConnectionState();
	private static final SelectionState selectionState = new SelectionState();
	private State currentState = termState;

	public void setTermState() {
		currentState = termState;
	}

	public void setConnectionState() {
		currentState = connectionState;
	}

	public void setSelectionState() {
		currentState = selectionState;
	}

	public void setDeleteState() {
		currentState = deleteState;
	}

	public void setEditState() {
		currentState = editState;
	}
	public State getState() {
		return getCurrentState();
	}

}
