package raf.dsw.gerumap.app.gui.swing.view;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.JPanel;
import raf.dsw.gerumap.app.AppCore;
import raf.dsw.gerumap.app.mapRepository.MapNode;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.Project;
import raf.dsw.gerumap.app.mapRepository.model.ProjectExplorer;
import raf.dsw.gerumap.app.observer.IPublisher;
import raf.dsw.gerumap.app.observer.IPublisherImpl;
import raf.dsw.gerumap.app.observer.ISubscriber;

public class ProjectViewManager extends IPublisherImpl implements ISubscriber {

	private static ProjectViewManager instance;
	private final JPanel empty;
	private final HashMap<Project, ProjectView> map = new HashMap<>();
	private Component projectView;

	private ProjectViewManager() {
		AppCore.getInstance().getMapRepository().getProjectExplorer().addSubscriber(this);
		empty = new JPanel();
		projectView = empty;
	}

	public static ProjectViewManager getInstance() {
		if (instance == null) {
			instance = new ProjectViewManager();
		}
		return instance;
	}

	public Component getProjectView() {
		return projectView;
	}

	public void setProjectView(Project p) {
		addProject(p);
		projectView = map.get(p);
		publish();
	}

	private void addProject(Project p) {
		if (!map.containsKey(p)) {
			ProjectView pv = new ProjectView(p);
			map.put(p, pv);
			for (MapNode node : p.getChildren()) {
				pv.addMindMapView((MindMap) node);
			}
		}
	}

	public void removeProject(Project p) {
		map.remove(p);
		if (map.isEmpty()) {
			projectView = empty;
		}
		publish();
	}

	@Override
	public void update(IPublisher publisher) {
		if (publisher instanceof ProjectExplorer projectExplorer) {
			Project toRemove = null;
			for (Project p : map.keySet()) {
				if (!(projectExplorer.getChildren().contains(p))) {
					toRemove = p;
				}
			}
			removeProject(toRemove);
		}
	}
}
