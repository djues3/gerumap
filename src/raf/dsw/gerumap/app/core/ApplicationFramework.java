package raf.dsw.gerumap.app.core;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public abstract class ApplicationFramework {
    protected GUI gui;

    public abstract void run();
    public void init(GUI gui) {
        this.gui = gui;
    }
}
