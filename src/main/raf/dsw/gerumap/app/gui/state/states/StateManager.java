package raf.dsw.gerumap.app.gui.state.states;

import lombok.Getter;
import raf.dsw.gerumap.app.gui.state.State;
import raf.dsw.gerumap.app.gui.swing.controller.state.MoveStateAction;

@Getter
public class StateManager {
	private static final TermState termState = new TermState();
	private static final EditState editState = new EditState();
	private static final DeleteState deleteState = new DeleteState();
	private static final LinkState linkState = new LinkState();

	private static final MoveState moveState = new MoveState();

	public ZoomState getZoomState() {
		return zoomState;
	}

	private final ZoomState zoomState = new ZoomState();

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	private SelectionState selectionState = new SelectionState();
	private State currentState = termState;

	public void setTermState() {
		currentState = termState;
	}

	public void setLinkState() {
		currentState = linkState;
	}

	public void setSelectionState() {
		currentState = selectionState;
	}

	public void setMoveState() {
		currentState = moveState;
	}

	public void setDeleteState() {
		currentState = deleteState;
	}
	public void setEditState() {
		currentState = editState;
	}

	public void setZoomState() {
		currentState = zoomState;
	}

	public State getState() {
		return getCurrentState();
	}
}
