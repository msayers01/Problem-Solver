package framework.solution;

import framework.graph.Vertex;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author tcolburn, Michael Sayers
 */
public class Solution implements Iterator<Vertex> {
    
    /**
     * Creates an object that represents a path from a start
     * vertex to an end vertex in a problem solving domain.
     * This constructor will be called after a search has been
     * initiated on the start vertex.
     * If a path from start to end exists, it can be constructed
     * from the predecessor information stored in the end vertex.
     * @param start the start vertex
     * @param end the end vertex
     */
    public Solution(Vertex start, Vertex end) {
        stack.push(end);
        pred = end.getPredecessor();
        while(pred != null){
            stack.push(pred);
            pred = pred.getPredecessor();
        }    
    }

    /**
     * Gets the length of the solution.
     * This is the number of moves in the solution, not the
     * number of vertices.
     * @return the solution length
     */
    public int getLength() {
        // You must provide
        return stack.size()-1;
    }
    
    /**
     * Checks whether there are more vertices in this solution.
     * @return true if there are more vertices in this solution, false
     * otherwise
     */
    @Override
    public boolean hasNext() {
        // You must provide
        if(!stack.isEmpty()) return true;
        else return false;
    }
    
    /**
     * Removes and returns the next vertex in this solution.
     * @return the next vertex in this solution
     * @throws RuntimeException if there are no more vertices
     */
    @Override
    public Vertex next() {
        if(hasNext()){
            return stack.pop();
        }
        else return null;
    }
    
    // Private instance fields here
    private final Stack<Vertex> stack = new Stack<>();
    private Vertex pred;
}