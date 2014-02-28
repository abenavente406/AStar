package ambenavente1.cs151.astar;

import java.awt.*;
import java.util.List;

/**
 * Represents an individual node in the search grid.
 *
 * @author Anthony Benavente
 * @version 2/24/14
 */
public class Node {

    /**
     * The parent node to this node
     */
    private Node parent;

    /**
     * The x coordinate location of this node
     */
    private int x;

    /**
     * The y coordinate location of this node
     */
    private int y;

    /**
     * The total distance traveled from the start point to this node (g)
     */
    private double distanceTraveled;

    /**
     * The estimated distance to the goal (heuristic/h)
     */
    private double distanceToGoal;

    /**
     * If things can "walk" through this node
     */
    private boolean walkable;

    /**
     * If this node can be found in the openList for the search
     */
    private boolean inOpenList;

    /**
     * If this node can be found in the closedList for the search
     */
    private boolean inClosedList;

    /**
     * The nodes that directly neighbor this node
     */
    private List<Node> neighbors;

    /**
     * Creates a node with a specified x and y value.
     *
     * @param x The x coordinate for this node
     * @param y The y coordinate for this node
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distanceToGoal = 0;
        this.distanceTraveled = 0;
        this.walkable = true;
        this.inOpenList = false;
        this.inClosedList = false;
    }

    /**
     * Gets the x coordinate for this node
     *
     * @return The x coordinate for this node
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate for this node
     *
     * @return The y coordinate for this node
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the distance from the start node to this one
     *
     * @return The distance from the start node to this one (<em>g(x)</em>)
     */
    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Sets the distance from the start node to this one
     *
     * @param distanceTraveled distance from the start node to this one
     *                         (<em>g(x)</em>)
     */
    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    /**
     * Gets the approximate distance from the goal node to this one
     *
     * @return The approximate distance from the goal to this node
     * (<em>h(x)</em>)
     */
    public double getDistanceToGoal() {
        return distanceToGoal;
    }

    /**
     * Sets the approximate distance from the goal to this node  (usually
     * calculated with a heuristic function)
     *
     * @param distanceToGoal The approximate distance from the goal node to
     *                       this one (<em>h(x)</em>)
     */
    public void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    /**
     * Gets the parent node to this node (start node has no parent)
     *
     * @return The parent node to this node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent node to this node (start node has no parent)
     * @param parent The parent node to this node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Gets if things can "walk" through this node
     *
     * @return If things can "walk" through this node
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * Sets if things can "walk" through this node
     *
     * @param walkable If things can "walk" through this node
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * Gets if this node hasn't already been visited by the pathfinding
     * algorithm
     *
     * @return If this node is in the openList
     */
    public boolean isInOpenList() {
        return inOpenList;
    }

    /**
     * Sets if this node is eligible to be visited
     *
     * @param inOpenList If the node should be in the openList
     */
    public void setInOpenList(boolean inOpenList) {
        this.inOpenList = inOpenList;
    }

    /**
     * Gets if this node has already been visited by the path-finding
     * algorithm
     *
     * @return If this node is in the closedList
     */
    public boolean isInClosedList() {
        return inClosedList;
    }

    /**
     * Sets if this node should not be visited
     *
     * @param inClosedList If this node should be in the closedList
     */
    public void setInClosedList(boolean inClosedList) {
        this.inClosedList = inClosedList;
    }

    /**
     * Gets the nodes neighboring this node
     *
     * @return A list containing the nodes neighboring this one
     */
    public List<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Sets the nodes that neighbor this node
     *
     * @param neighbors A list containing the nodes neighboring this one
     */
    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Gets a point that contains this node's x and y coordinate
     *
     * @return The point position of this node
     */
    public Point getPos() {
        return new Point(x, y);
    }
}
