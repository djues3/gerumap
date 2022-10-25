package raf.dsw.gerumap.app.core;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public abstract class ApplicationFramework {
    protected GUI gui;
    protected MapRepository mapRepository;

    public abstract void run();
    public void init(GUI gui, MapRepository mapRepository) {
        this.gui = gui;
        this.mapRepository = mapRepository;
    }
}
