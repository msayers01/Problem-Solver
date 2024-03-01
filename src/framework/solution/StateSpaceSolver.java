package framework.solution;

import framework.graph.Vertex;
import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a state space solver by extending the abstract
 * Solver class.
 * @author Your name and section here
 */
public abstract class StateSpaceSolver extends Solver {
    
    /**
     * Creates a BFS or DFS state space solver.
     * This constructor should set the queue to a double-ended queue 
     * (DEQUE) like the GraphSolver.
     * @param problem the problem being solved
     */
    public StateSpaceSolver(Problem problem) {
        super(problem);
        super.setQueue(new LinkedList<>());
    }
    
    /**
     * This method implements the expand operation required by the 
     * state space search algorithm.
     * @param u the vertex being expanded
     * @return the children of u in the state space search tree
     */
    @Override
    public List<Vertex> expand(Vertex u) { 
        // you must provide
        Mover mover = super.getProblem().getMover();
        List<String> moveNames = mover.getMoveNames();
        List<Vertex> children = new LinkedList<>();
        State currState = (State)u.getData();
        
        for(String moves : moveNames){
            State child = mover.doMove(moves, currState);
            
            if(child != null) {
                child.setMove(moves);
                children.add(new Vertex(child));
            }
        }
        return children;
    }
}