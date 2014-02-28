package ambenavente1.cs151.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to find the shortest path to a point using the
 * A* algorithm.
 *
 * @author Anthony Benavente
 * @version 2/24/14
 */
public class AStar {

    /**
     * The grid that contains data of walkable nodes or not and the costs
     */
    private Grid grid;

    /**
     * List of nodes that need to be visited
     */
    private List<Node> openList;

    /**
     * List of nodes that have already been visited
     */
    private List<Node> closedList;

    /**
     * Creates a default AStar object with an empty grid
     */
    public AStar() {
        this(new Grid());
    }

    /**
     * Create an AStar object passing in a grid object whose cells have already
     * been chosen to be walkable or not
     *
     * @param grid The grid of nodes needed to find the shortest path
     */
    public AStar(Grid grid) {
        this.grid = grid;
        resetLists();
    }


    /**
     * Resets the lists and the grid
     */
    private void resetLists() {
        openList = new ArrayList<Node>();
        closedList = new ArrayList<Node>();
        grid.reset();
    }

    /**
     * Calculates the shortest path using an iterative A* algorithm adapted
     * from the book <em>XNA 4.0 Game Development by Example: Create
     * exciting games with Microsoft XNA 4.0 - Beginner's Guide</em> by
     * Kurt Jaegers
     *
     * @param startPoint The starting point; where the search begins
     * @param endPoint   The ending point; the goal to where the search will
     *                   end
     * @return A list of points that lead up from the startPoint to the
     * endPoint
     */
    public List<Point> calculatePath(Point startPoint, Point endPoint) {
        resetLists();

        Node start = grid.getNode(startPoint);
        Node end   = grid.getNode(endPoint);

        start.setInOpenList(true);
        start.setDistanceToGoal(heuristic(startPoint, endPoint));
        start.setDistanceTraveled(0);
        openList.add(start);

        while (openList.size() > 0) {
            Node current = getBestNode();

            if (current.equals(end)) {
                return getFinalPath(start, end);
            }

            for (Node neighbor : current.getNeighbors()) {
                if (neighbor != null && neighbor.isWalkable()) {
                    double heuristic = heuristic(neighbor.getPos(), endPoint);
                    double distanceTraveled = current.getDistanceTraveled() + heuristic;

                    if (!neighbor.isInOpenList() &&
                        !neighbor.isInClosedList()) {
                        neighbor.setDistanceTraveled(distanceTraveled + heuristic);
                        neighbor.setDistanceTraveled(distanceTraveled);
                        neighbor.setParent(current);
                        neighbor.setInOpenList(true);
                        openList.add(neighbor);
                    } else if (neighbor.isInClosedList() || neighbor.isInOpenList()) {
                        if (neighbor.getDistanceTraveled() > distanceTraveled) {
                            neighbor.setDistanceTraveled(distanceTraveled);
                            neighbor.setDistanceToGoal(distanceTraveled + heuristic);
                            neighbor.setParent(current);
                        }
                    }
                }
            }

            openList.remove(current);
            current.setInClosedList(true);
        }
        return new ArrayList<Point>();
    }

    /**
     * Gets the final path by collapsing nodes into the closedList
     *
     * @param start The starting point of the search
     * @param end   The ending point of the search
     * @return A list of points that were collapsed starting with the end
     * node and following the parents
     */
    private List<Point> getFinalPath(Node start, Node end) {
        closedList.add(end);

        Node parent = end.getParent();

        while (parent != start) {
            closedList.add(parent);
            parent = parent.getParent();
        }

        List<Point> finalPath = new ArrayList<Point>();

        for (int i = closedList.size() - 1; i >= 0; i--) {
            finalPath.add(new Point(closedList.get(i).getX(),
                                    closedList.get(i).getY()));
        }

        return finalPath;
    }

    /**
     * The heuristic function used to estimate the distance from Point, a, to
     * Point, end
     *
     * @param a The point to estimate the distance from
     * @param end The point to estimate the distance to
     * @return An estimate of the distance between a and end using the
     * Manhattan distance formula <em>(abs(a.x - end.x) + abs(a.y - end.y)</em>
     */
    private double heuristic(Point a, Point end) {
        return (Math.abs(a.x - end.x)) +
                (Math.abs(a.y - end.y));
    }

    /**
     * Gets the best node in the open list based on its cost value
     * (distanceToGoal)
     *
     * @return The best node that probably leads to the shortest path
     */
    private Node getBestNode() {
        double minCost = Double.MAX_VALUE;
        Node bestNode  = null;

        for (Node node : openList) {
            if (node.getDistanceToGoal() < minCost) {
                minCost = node.getDistanceToGoal();
                bestNode = node;
            }
        }

        return bestNode;
    }

    /**
     * Gets the grid used by this object in order to calculate the shortest
     * path
     *
     * @return the grid used by this object
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Sets the grid that this object will use when calculating the shortest
     * path
     *
     * @param grid The grid of nodes needed to find the shortest path
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
