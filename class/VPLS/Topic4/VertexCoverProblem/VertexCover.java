import java.util.Set;
import java.util.HashSet;

public class VertexCover {

	/*
     Available methods from  Graph class:
        public void addEdge(int i, int j);
        public void removeEdge(Integer i, Integer j);
        public int numEdges();
        public int degree(Integer i);
        public Set<Integer> successors(Integer i);
        public Set<Integer> connectedNodes();
        public Set<Integer> nodes();
    */

	/*
	The Vertex Cover Problem consists of selecting the smallest possible set of vertices in a graph such that every edge is "covered" (meaning at least one endpoint of every edge belongs to the selected set).

    Input: An undirected graph G = (V, E)

    Output: A subset of vertices S ⊆ V

    Objective: Minimize the cardinality | S |

    Constraint: For every edge {u, v} ∈ E, u ∈ S or v ∈ S.
	 */

	// Solution: A set of Vertices. They can't be repeated and the order doesn't really matter. S must be included in V.
	//		Hence, S = {v_1, v_2, ... v_n}

	// Objective function: min(f(S)) = |S|

		// Decision:

	public static Set<Integer> getMinimumVertexCover(Graph g) {
		Set<Integer> sol = new HashSet<>();

		while (g.numEdges() != 0){
			int mostConnected = 0;
			int connections = 0;

			for (int i : g.nodes()){
				if (connections < g.degree(i)){
					mostConnected = i;
					connections = g.degree(i);
				}
			}

			// remove it from the list

			for (int i : g.successors(mostConnected)){
				g.removeEdge(mostConnected, i);
			}

			sol.add(mostConnected);
		}

		return sol;
	}

}
