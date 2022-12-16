package raf.dsw.gerumap.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.observer.ISubscriber;
import raf.dsw.gerumap.app.gui.state.states.StateManager;
import raf.dsw.gerumap.app.gui.swing.controller.MouseController;
import raf.dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import raf.dsw.gerumap.app.gui.swing.view.painter.Painter;
import raf.dsw.gerumap.app.gui.swing.view.painter.TermPainter;
import raf.dsw.gerumap.app.mapRepository.model.MindMap;
import raf.dsw.gerumap.app.mapRepository.model.elements.Link;
import raf.dsw.gerumap.app.mapRepository.model.elements.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MindMapView extends JPanel implements ISubscriber {
    private MindMap mindMap;
    private StateManager stateManager;

    private AffineTransform affineTransform;

    private Set<Painter> painters = new HashSet<>();

    public MindMapView(MindMap mindMap) {
        this.mindMap = mindMap;
        MouseController mouseController = new MouseController(this);
        this.addMouseListener(mouseController);
        this.addMouseMotionListener(mouseController);
        this.addMouseWheelListener(mouseController);
        this.mindMap.addSubscriber(this);
    }

    @Override
    public void update(IPublisher publisher) {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Painter painter : painters) {
            if(painter instanceof LinkPainter lp)
                lp.draw(g);
        }
        for (Painter painter : painters) {
            if(painter instanceof TermPainter tp)
                tp.draw(g);
        }
    }
    public void addPainter(Painter painter) {
        painters.add(painter);
    }
    public void removePainter(Painter painter) {
        if(painter instanceof TermPainter tp) {
            mindMap.getChildren().remove(tp.getTerm());
        }
        if(painter instanceof LinkPainter lp) {
            mindMap.getChildren().remove(lp.getLink());
        }
        painters.remove(painter);
    }
    public List<TermPainter> getTermsInRectangle(Rectangle2D.Double bounds) {
        List<TermPainter> terms = new ArrayList<>();
        for (Painter painter : painters) {
            if (painter instanceof TermPainter tp) {
                if (tp.getShape().intersects(bounds) || bounds.contains(tp.getShape().getBounds())) {
                    terms.add(tp);
                }
            }
        }
        return terms;
    }


    public List<LinkPainter> getLinksInRectangle(Rectangle2D.Double aDouble) {
        List<LinkPainter> links = new ArrayList<>();
        for (Painter painter : painters) {
            if (painter instanceof LinkPainter lp) {
                if (lp.getShape().intersects(aDouble) || aDouble.contains(lp.getShape().getBounds())) {
                    links.add(lp);
                }
            }
        }
        return links;
    }
	public TermPainter getPainterForTerm(Term term) {
	            for(Painter painter : painters) {
            if(painter instanceof TermPainter tp) {
                if(tp.getTerm().equals(term))
                    return tp;
            }
        }
        return null;
    }

    public Painter getPainterForLink(Link link) {
        for(Painter painter : painters) {
            if(painter instanceof LinkPainter lp) {
                if(lp.getLink().equals(link))
                    return painter;
            }
        }
        return null;
    }
}
