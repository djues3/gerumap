package raf.dsw.gerumap.app.gui.state;

import java.util.List;
import lombok.Getter;
import raf.dsw.gerumap.app.gui.state.states.DeleteState;
import raf.dsw.gerumap.app.gui.state.states.EditState;
import raf.dsw.gerumap.app.gui.state.states.LinkState;
import raf.dsw.gerumap.app.gui.state.states.MoveState;
import raf.dsw.gerumap.app.gui.state.states.SelectionState;
import raf.dsw.gerumap.app.gui.state.states.TermState;
import raf.dsw.gerumap.app.gui.state.states.ZoomState;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;

@Getter
public class StateManager {

	private final TermState termState = new TermState();
	private final EditState editState = new EditState();
	private final DeleteState deleteState = new DeleteState();
	private final LinkState linkState = new LinkState();
	private final MoveState moveState = new MoveState();
	private final ZoomState zoomState = new ZoomState();
	private final SelectionState selectionState = new SelectionState();
	private State currentState = termState;

	public void setTermState() {
		selectionState.clearSelected();
		currentState = termState;
	}

	public void setLinkState() {
		selectionState.clearSelected();
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
		if (editState.tryToEdit()) {
			return;
		}
		currentState = editState;
	}

	public void setZoomState() {
		currentState = zoomState;
	}

	public State getState() {
		return getCurrentState();
	}

	public List<TermPainter> getSelectedTerms() {
		return selectionState.getSelectedTerms();
	}
	public void clearSelected() {
		selectionState.clearSelected();
	}

}
