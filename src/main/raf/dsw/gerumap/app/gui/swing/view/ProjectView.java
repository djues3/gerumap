package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.gui.state.states.StateManager;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
public class ProjectView extends JPanel implements ISubscriber {
    private Project project;
    private JTabbedPane tabs;
    private JPanel labels;
    private JLabel authorLabel;
    private JLabel nameLabel;
    private HashMap<MindMap, MindMapView> map = new HashMap<>();
	private StateToolbar stateToolbar = new StateToolbar();
	private StateManager stateManager;

	private void addMindMapView(MindMap m) {
		if (!map.containsKey(m)) {
			map.put(m, new MindMapView(m));
			m.addSubscriber(this);
		}
	}

	private void removeMindMapView(MindMap m) {
		if (map.containsKey(m)) {
			map.remove(m);
			m.removeSubscriber(this);
		}
	}

	public ProjectView(Project p) {
		project = p;
		project.addSubscriber(this);

		labels = new JPanel();

		nameLabel = new JLabel(project.getName());
		authorLabel = new JLabel("Author : " + project.getAuthor());

		labels.setMinimumSize(new Dimension(MainFrame.getInstance().getWidth(), 20));
		labels.setMaximumSize(new Dimension(MainFrame.getInstance().getWidth(), 20));
		labels.setLayout(new BorderLayout());
		labels.add(authorLabel, BorderLayout.EAST);
		labels.add(nameLabel, BorderLayout.WEST);

		tabs = new JTabbedPane();

		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);

		this.add(labels);
		this.add(stateToolbar);
		this.add(tabs);
		for (MapNode x : p.getChildren()) {
			x.addSubscriber(this);
		}
		for (MapNode x : project.getChildren()) {
			addMindMapView((MindMap)x);
		}
		this.stateManager = new StateManager();
	}


	@Override
	public void update(IPublisher publisher) {
		nameLabel.setText(project.getName());
		authorLabel.setText("Author : " + project.getAuthor());
		HashSet<MindMap> toRemove = new HashSet<>();

		for (MindMap m : map.keySet()) {
			if (!project.getChildren().contains(m)) {
				toRemove.add(m);
				for (int i = 0, size = tabs.getComponentCount() ; i < size; i++) {
					if (((MindMapView)tabs.getComponentAt(i)).getMindMap() == m) {
						this.tabs.removeTabAt(i);
						break;
					}
				}
			}
		}


		for (MapNode mapNode : project.getChildren()) {
			MindMap m = (MindMap)mapNode;
			if (!map.containsKey(m)){
				addMindMapView(m);
				tabs.addTab(m.getName(), map.get(m));
			}
		}
		for (MindMap m : toRemove)
			removeMindMapView(m);

		for (int i = 0 , size = tabs.getComponentCount() ; i < size ; i++) {
			this.tabs.setTitleAt(i, ((MindMapView) tabs.getComponentAt(i)).getMindMap().getName());
		}
	}
    public void startTermState() {
        stateManager.setTermState();
    }
    public void startSelectionState() {
        stateManager.setSelectionState();
    }
	public void startDeleteState() {
        stateManager.setDeleteState();
    }
	public void startEditState() {
        stateManager.setEditState();
    }
	public void startLinkState() {
		this.stateManager.setLinkState();
	}
	public void startMoveState() {
		this.stateManager.setMoveState();
	}
	public void startZoomState() {
		this.stateManager.setZoomState();
	}

	public void mousePressed(int x, int y, MindMapView view) {
        this.stateManager.getState().mousePressed(x, y, view);
	}

	public void mouseDragged(int x, int y, MindMapView view) {
        this.stateManager.getState().mouseDragged(x, y, view);
    }

	public void mouseClicked(int x, int y, MindMapView view) {
		this.stateManager.getState().mouseClicked(x, y, view);
	}

	public void mouseReleased(int x, int y, MindMapView view) {
		this.stateManager.getState().mouseReleased(x, y, view);
	}

	public void mouseMoved(int x, int y, MindMapView view) {
		this.stateManager.getState().mouseMoved(x, y, view);
	}
}
