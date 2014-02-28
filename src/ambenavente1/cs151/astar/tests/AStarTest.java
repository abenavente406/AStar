package ambenavente1.cs151.astar.tests;

import ambenavente1.cs151.astar.AStar;
import ambenavente1.cs151.astar.Grid;

import java.awt.Point;
import java.util.List;

/**
 * Text test for the AStar algorithm
 *
 * @author Anthony Benavente
 * @version 2/24/14
 */
public class AStarTest {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        for (int y = 4; y < 10; y++) {
            grid.setWalkable(3, y, true);
        }
        AStar astar = new AStar(grid);
        List<Point> points =
                astar.calculatePath(new Point(0, 0), new Point(7, 9));

        for (Point p : points)
            System.out.println(p);
    }

}

