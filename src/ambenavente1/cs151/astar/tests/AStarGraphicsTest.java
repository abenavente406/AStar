package ambenavente1.cs151.astar.tests;

import ambenavente1.cs151.astar.AStar;
import ambenavente1.cs151.astar.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Test for the AStar algorithm
 *
 * @author Anthony Benavente
 * @version 2/25/14
 */
public class AStarGraphicsTest extends JFrame {

    private static final int CELL_SIZE = 10;

    private Grid grid;
    private AStar aStar;
    private Point start;
    private Point end;
    private Drawer drawer;
    private JLabel label;
    private boolean drawing;
    private int buttonDown;

    public AStarGraphicsTest(int width, int height, Point start, Point end) {
        this.start = start;
        this.end = end;
        this.grid = new Grid(width, height);
        this.drawing = false;
        this.buttonDown = -1;

        this.aStar = new AStar(grid);

        label = new JLabel();
        label.setSize(200, 20);
        label.setVisible(true);
        drawer = new Drawer();
        drawer.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                drawing = true;
                buttonDown = e.getButton();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawing = false;
                buttonDown = -1;
            }

        });

        drawer.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (drawing) {
                    Point mousePos = e.getPoint();
                    if (buttonDown == MouseEvent.BUTTON1) {
                        if (mousePos.x >= 0 && mousePos.x < grid.getWidth() &&
                                mousePos.y >= 0 && mousePos.y < grid.getHeight()) {
                            grid.setWalkable(mousePos.x, mousePos.y, false);
                            repaint();
                        }
                    } else if (buttonDown == MouseEvent.BUTTON3) {
                        if (mousePos.x >= 0 && mousePos.x < grid.getWidth() &&
                                mousePos.y >= 0 && mousePos.y < grid.getHeight()) {
                            grid.setWalkable(mousePos.x, mousePos.y, true);
                            repaint();
                        }
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point mousePos = e.getPoint();
                if (buttonDown == MouseEvent.BUTTON1) {
                    if (mousePos.x / CELL_SIZE >= 0 &&
                            mousePos.x / CELL_SIZE < grid.getWidth() &&
                            mousePos.y / CELL_SIZE >= 0 &&
                            mousePos.y / CELL_SIZE < grid.getHeight()) {
                        grid.setWalkable(mousePos.x / CELL_SIZE,
                                mousePos.y / CELL_SIZE,
                                false);
                        drawer.repaint();
                    }
                } else if (buttonDown == MouseEvent.BUTTON3) {
                    if (mousePos.x / CELL_SIZE >= 0 &&
                            mousePos.x / CELL_SIZE < grid.getWidth() &&
                            mousePos.y / CELL_SIZE >= 0 &&
                            mousePos.y / CELL_SIZE < grid.getHeight()) {
                        grid.setWalkable(mousePos.x / CELL_SIZE,
                                mousePos.y / CELL_SIZE,
                                true);
                        drawer.repaint();
                    }
                }
            }
        });

        drawer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    for (int y = 0; y < grid.getHeight(); y++) {
                        for (int x = 0; x < grid.getWidth(); x++) {
                            grid.setWalkable(x, y, true);
                            drawer.repaint();
                        }
                    }
                }
            }
        });

        getContentPane().add(drawer);
        getContentPane().add(label, BorderLayout.AFTER_LAST_LINE);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class Drawer extends Canvas {

        public Drawer() {
            setSize(grid.getWidth() * CELL_SIZE, grid.getHeight() * CELL_SIZE);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    Color tmp = g.getColor();
                    if (!grid.isWalkable(x, y)) {
                        g.setColor(Color.black);
                        g.fillRect(x * CELL_SIZE,
                                y * CELL_SIZE,
                                CELL_SIZE,
                                CELL_SIZE);
                    } else {
                        g.setColor(Color.black);
                        g.drawRect(x * CELL_SIZE,
                                y * CELL_SIZE,
                                CELL_SIZE,
                                CELL_SIZE);
                    }
                    g.setColor(tmp);
                }
            }

            g.setColor(Color.green);
            g.fillRect((int) start.getX() * CELL_SIZE,
                    (int) start.getY() * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE);

            g.setColor(Color.red);
            g.fillRect((int) end.getX() * CELL_SIZE,
                    (int) end.getY() * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE);

            List<Point> path = aStar.calculatePath(start, end);
            for (Point p : path) {
                if (!p.equals(end)) {
                    g.setColor(new Color(233, 229, 22, 155));
                    g.fillRect((int) p.getX() * CELL_SIZE,
                            (int) p.getY() * CELL_SIZE,
                            CELL_SIZE,
                            CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new AStarGraphicsTest(20, 20, new Point(0, 0), new Point(10, 10));
    }
}