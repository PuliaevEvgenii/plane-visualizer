package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import com.company.models.Parallelepiped;
import com.company.utils.screen.drawers.Drawer;
import com.company.utils.screen.drawers.SimpleEdgeDrawer;
import com.company.utils.screen.fillers.Filler;
import com.company.utils.screen.fillers.SimplePolygonFiller;
import com.company.utils.math.Vector3;
import com.company.models.FunctionModel;
import com.company.utils.screen.ScreenConverter;
import com.company.third_dim.SimpleCamera;
import com.company.third_dim.Scene;

public class DrawPanel extends JPanel implements SimpleCameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private SimpleCamera camera;
    private SimpleCameraController camController;
    
    public DrawPanel() {
        super();
        sc = new ScreenConverter(-6, 6, 12, 12, getWidth(), getHeight());
        camera = new SimpleCamera();
        camController = new SimpleCameraController(camera, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

        scene.getModelsList().add(new Parallelepiped(
                new Vector3(-10f, -10f, -10f),
                new Vector3(10f, 10f, 10f)
        ));

        scene.getModelsList().add(new FunctionModel(
                (x, y) -> (float) (0.2*Math.pow(x, 2) + 0.2*Math.pow(y, 2) - 9.99f),
                //(x, y) -> (float) (Math.exp(Math.sin(x * 3)) - Math.cos(y * y)),
                //(x, y) -> (float) (Math.pow(x, 3) - Math.pow(y, 2)),
                //(x, y) -> (float) (Math.pow(x, 1.0f / 3) * Math.sin(y)),
                //(x, y) -> (float) (Math.log(x * x + 1) / (y * y + 2)),
                //(x, y) -> (float) ((1 / (x*x*x + 2)) + Math.log(y)),
                //(x, y) -> (float) (Math.abs(Math.pow(y, 4) - Math.pow(y, 3) + x*x - x)),
                //(x, y) -> (float) (Math.pow(x, 4) + Math.pow(y, 4) * (x*x + y*y)),
                //(x, y) -> (float) (Math.exp((Math.sin(x) + Math.cos(x)) / (x*x + 1))),
                50,
                50,
                new Vector3(-10f, -10f, -10f),
                new Vector3(10f, 10f, 10f)
        ));
        
        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }
    
    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bi.getGraphics();
        Drawer drawer = new SimpleEdgeDrawer(sc, graphics2D);
        Filler filler = new SimplePolygonFiller(sc, graphics2D);
        filler.showGrid();
        scene.drawScene(drawer, filler,  camera);
        g.drawImage(bi, 0, 0, null);
        graphics2D.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}
