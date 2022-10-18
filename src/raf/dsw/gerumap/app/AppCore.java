package raf.dsw.gerumap.app;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.core.AppFramework;
import raf.dsw.gerumap.app.core.GUI;
import raf.dsw.gerumap.app.gui.swing.SwingGui;

@Getter
@Setter
public class AppCore {
    private static AppCore instance;
    private AppFramework appFramework;
    private AppCore() {

    }

    public static AppCore getInstance() {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }

    public static void main(String[] args) {
        GUI gui = new SwingGui();
    }
}
