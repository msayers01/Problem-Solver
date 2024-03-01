package framework.solution;

import framework.graph.Vertex;
import framework.problem.Problem;
import java.util.LinkedList;

/**
 * A problem solver that does a breadth-first search of
 * the problem graph.
 * @author tcolburn
 */
public class BFSGraphSolver extends GraphSolver {
    
    public BFSGraphSolver(Problem problem) {
        super(problem);
        super.getStatistics().setHeader("Breadth-First Graph Search");
    }
    
    @Override
    public void add(Vertex v) {
        ((LinkedList<Vertex>)getQueue()).addLast(v); // BFS
    }
    
}