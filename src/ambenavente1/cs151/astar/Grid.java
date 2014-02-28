package ambenavente1.cs151.astar;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a 2D grid of Nodes that contain information on walkability and
 * costs. In order to use this with a custom map, you must set the walkability
 * of each Node with the setWalkable() function.
 * <p></p>
 * Example: If I had a Grid object, <em>grid</em>, and a World object,
 * <em>world</em>, that contained information on if a certain tile at a given
 * point is a wall or not, I could transfer that data using:
 * <code>
 *     <em>grid.setWalkable(x, y, !world.isWall(x, y))</em>
 * </code>
 *
 * @author Anthony Benavente
 * @version 2/24/14
 */
public class Grid {

    /**
     * The number of nodes wide the grid is
     */
    private int width;

    /**
     * The number of nodes tall the grid is
     */
    private int height;

    /**
     * The 2D array of nodes used as the grid
     */
    private Node[][] grid;

    /**
     * Creates a default grid with 0 x 0 nodes in it
     */
    public Grid() {
        this(0, 0);
    }

    /**
     * Creates a grid with specified dimensions
     *
     * @param width The number of nodes wide
     * @param height The number of nodes in the tall
     */
    public Grid(int width, int height) {
        this.width  = width;
        this.height = height;
        this.grid   = new Node[height][width];

        initNodes();
    }

    /**
     * Initializes the nodes by calling the reset function
     */
    private void initNodes() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Node(x, y);
            }
        }

        reset();
    }

    /**
     * Sets if a node at a certain point is walkable or not.  <em>WARNING:
     * This function will crash if x or y are not in bounds.</em>
     *
     * @param x The x coordinate of the node to set walkable
     * @param y The y coordinate of the node to set walkable
     * @param walkable If the cell at the given point is walkable or not.
     *                 Typically, this leads to wall-tiles being not walkable
     *                 (false) and floor-tiles being walkable (true)
     */
    public void setWalkable(int x, int y, boolean walkable) {
        grid[y][x].setWalkable(walkable);
    }

    /**
     * Gets if the node at the specified point is walkable or not
     *
     * @param x The x coordinate of the node to check
     * @param y The y coordinate of the node to check
     * @return If the node allows the ability for anything to "walk" through it
     * (walkablilty)
     */
    public boolean isWalkable(int x, int y) {
        return grid[y][x].isWalkable();
    }

    /**
     * Gets the node at a given point.  This method will crash the program if
     * x or y are outside of the bounds of the grid.
     *
     * @param x The x coordinate of the node
     * @param y The y coordinate of the node
     * @return The node at the specified x and y coordinates
     */
    public Node getNode(int x, int y) {
        return getNode(new Point(x, y));
    }

    /**
     * Gets the node at a given point.  This method will crash the program if
     * x or y are outside of the bounds of the grid.
     *
     * @param p The point of the node
     * @return The node at the specified point
     */
    public Node getNode(Point p) {
        return grid[p.y][p.x];
    }

    /**
     * Resets the nodes of the grid by setting their inClosedList and
     * inOpenList values to false and also resetting each of its neighbors
     */
    public void reset() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = grid[y][x];
                node.setInClosedList(false);
                node.setInOpenList(false);

                List<Point> neighbors = new ArrayList<Point>();
                List<Node> nodes = new ArrayList<Node>();
                neighbors.add(new Point(x, y - 1));
                neighbors.add(new Point(x, y + 1));
                neighbors.add(new Point(x - 1, y));
                neighbors.add(new Point(x + 1, y));

                // Uncomment for diagonal movement
                // -------------------------------
//                neighbors.add(new Point(x - 1, y - 1));
//                neighbors.add(new Point(x + 1, y - 1));
//                neighbors.add(new Point(x - 1, y + 1));
//                neighbors.add(new Point(x + 1, y + 1));
//                neighbors.add(new Point(x - 1, y - 1));
//                neighbors.add(new Point(x - 1, y + 1));
//                neighbors.add(new Point(x + 1, y - 1));
//                neighbors.add(new Point(x + 1, y + 1));

                for (Point p : neighbors) {
                    if (p.x >= 0 && p.x < width
                            && p.y >= 0 && p.y < height) {
                        nodes.add(grid[p.y][p.x]);
                    }
                }

                node.setNeighbors(nodes);
            }
        }
    }

    /**
     * Gets the width of the grid by the number of tiles it has in the
     * x-dimension
     *
     * @return The width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the width of the grid by the number of tiles it has in the
     * y-dimension
     *
     * @return The height of the grid
     */
    public int getHeight() {
        return height;
    }
}
