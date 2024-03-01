package framework.solution;

import framework.graph.Vertex;
import framework.problem.Problem;
import framework.problem.State;
import static framework.solution.Statistics.CIRCULARITIES;
import static framework.solution.Statistics.LENGTH;
import static framework.solution.Statistics.NUM_OPS;
import static framework.solution.Statistics.QUEUE_SIZE;
import static framework.solution.Statistics.TIME;
import static framework.solution.Statistics.VERTICES;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * This abstract class implements a state space problem solver
 * that uses a queue to control its search. 
 * Subclasses must call the "setQueue" method to install the 
 * particular type of queue used.
 * Subclasses must also implement the abstract "expand" method.
 * Other protected methods can be overridden if needed.
 * This class also keeps and makes available problem solutions
 * and search statistics.
 * 
 * @author tcolburn
 */
public abstract class Solver {
    
    private final Problem problem;
    private Queue<Vertex> queue;
    private Solution solution;
    private final Statistics statistics;
    private final HashMap<Vertex, Vertex> vertices;
    private final static int INITIAL_CAPACITY = 500000;
    
    /**
     * Creates a solver object for a particular problem.
     * @param p the problem to be solved
     */
    public Solver(Problem p) {
        problem = p;
        statistics = new Statistics();
        vertices = new HashMap<>(INITIAL_CAPACITY);
    }

    /**
     * Getter for this solver's problem.
     * @return this solver's problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Setter for this solver's queue.
     * This must be called by a subclass.
     * @param q the queue to be used in this solver
     */
    public void setQueue(Queue<Vertex> q) {
        queue = q;
    }

    /**
     * Getter for this solver's queue.
     * @return this solver's queue
     */
    public Queue<Vertex> getQueue() {
        return queue;
    }

    /**
     * Getter for this solver's solution object.
     * @return this solver's solution object
     */
    public Solution getSolution() {
        return solution;
    }

    /**
     * Getter for this solver's statistics.
     * @return this solver's statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }
    
    /**
     * Expands a vertex in the state space search tree created by 
     * this solver.
     * This method is abstract and must be overridden by a subclass.
     * @param u the vertex to be expanded
     * @return a list of vertices that are one move away from u in
     * the state space
     */
    public abstract List<Vertex> expand(Vertex u);
    
    /**
     * Adds a vertex to the queue used by this solver.
     * May be overridden by subclasses.
     * @param v the vertex to be enqueued
     */
    protected void add(Vertex v) {
        getQueue().add(v);
    }
    
    /**
     * Gets the current state of the problem as a vertex.
     * May be overridden by subclasses.
     * @return the current state of the problem as a vertex
     */
    protected Vertex getCurrent() {
        return new Vertex(problem.getCurrentState());
    }
    
    /**
     * Attempts to solve the problem from the current state.
     * Search statistics are recorded, and if the search is
     * successful a solution object is constructed.
     */
    public void solve() {  
        statistics.clear();
        vertices.clear();
        Vertex current = getCurrent();
        Date before = new Date();
        Vertex goal = search(current);     
        Date after = new Date();
        statistics.putStat(VERTICES, vertices.size());
        statistics.putStat(TIME, (int)(after.getTime() - before.getTime()));
        if (goal != null) {
            solution = new Solution(current, goal);  
            statistics.putStat(LENGTH, solution.getLength());
        } else {
            throw new RuntimeException("Solution not found");
        }
    }
    
    
    /**
     * The central "search" method implements this algorithm:
  
       Search(s)
          d[s] = 0
          pred[s] = null
          Q = {s}
          V = {s}
          while Q ≠ {} do
             u = Remove[Q]
             if success(u) 
                then return u
             for each v ∈ expand(u) do
                if v ∉ V
                   then put v in V
                        d[v] = d[u] + 1
                        pred[v] = u
                        Add(Q,v)
          return null
 
     * This method must also update the queue statistics
     * (NUM_OPS and QUEUE_SIZE) and the number of CIRCULARITIES
     * encountered during the search.
     * @param s a vertex representing the current state of the
     * problem
     * @return a vertex representing a final state of the
     * problem if one is found, null otherwise
     */
    private Vertex search(Vertex s) {   
	// You must provide
        vertices.clear();
        getQueue().clear();
        
        s.setDistance(0);
        s.setPredecessor(null);
        add(s);
        getStatistics().incrStat(NUM_OPS);
        getStatistics().incrStat(QUEUE_SIZE);
        
        vertices.put(s, s);
        
        while(!getQueue().isEmpty())
        {
            Vertex u = getQueue().poll();
            getStatistics().incrStat(NUM_OPS);
            if(success(u))
            {
                return u;
            }
            
            for(Vertex v : expand(u))
            {
                if(!vertices.containsKey(v))
                {
                    vertices.put(u, v);
                    v.setDistance( u.getDistance() + 1);
                    v.setPredecessor(u);
                    add(v);
                    getStatistics().incrStat(NUM_OPS);
                    getStatistics().incrStat(QUEUE_SIZE);
                }
                else
                    getStatistics().incrStat(CIRCULARITIES);
            }
        }
        
        return null;
    }
    
    /* any private methods here */
    private Boolean success(Vertex u) {
        State s = (State)u.getData();
        if(s.equals(problem.getFinalState()))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}