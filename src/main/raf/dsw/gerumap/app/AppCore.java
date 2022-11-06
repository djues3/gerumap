package raf.dsw.gerumap.app;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.core.ApplicationFramework;
import raf.dsw.gerumap.app.core.GUI;
import raf.dsw.gerumap.app.core.MapRepository;
import raf.dsw.gerumap.app.gui.swing.SwingGui;
import raf.dsw.gerumap.app.mapRepository.MapRepositoryImpl;

@Getter
@Setter
public class AppCore extends ApplicationFramework {
    private static AppCore instance;
    private AppCore() {

    }

    public static AppCore getInstance() {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }

    public static void main(String[] args) {
        GUI gui = new SwingGui();
        MapRepository repo = new MapRepositoryImpl();
        ApplicationFramework app = AppCore.getInstance();
        app.init(gui, repo);
        app.run();
    }

    @Override
    public void run() {
        this.mapRepository.init();
        this.gui.run();
    }
}
