package framework.graph;

import framework.problem.Problem;
import framework.problem.State;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author Michael Sayers
 */
public class GraphCreator {
    
    public Graph createGraphFor(Problem problem) {
       
      graph = new Graph();
      queue = new LinkedList();
      start = new Vertex(problem.getInitialState());
      queue.add(start);
      moves = problem.getMover().getMoveNames();
      while(!queue.isEmpty()){
         u = queue.poll();
         for(int i = 0; i < moves.size(); i++)
         {
            next = problem.getMover().doMove(moves.get(i), (State)u.getData());
            if(next != null) 
            {
               v = new Vertex(next);
               if(graph.getVertices().containsKey(v))
               {
                  v = (Vertex)graph.getVertices().get(v);
               }
               else
               {
                  queue.add(v);
               }
               graph.addEdge(u, v);
            }
         }
      }
      
      
      return graph;
    }
    
    private Graph graph;
    private Queue<Vertex> queue;
    private Vertex start;
    private Vertex u;
    private Vertex v;
    private State next;
    private List<String> moves;
}

