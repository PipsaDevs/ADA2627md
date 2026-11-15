import java.util.List;
import java.util.ArrayList;

public class Antennas {
	
	private List<Integer> km; //Kilometre markers of the neighbourhood, listed in ascending order.
	private int coverage;

	/*
	Given an ordered set of N neighborhoods with known locations and a single type of antenna with
	a fixed coverage radius C, determine the optimal placement locations to install the minimum number
	of antennas such that every neighborhood is within range of at least one antenna.
	 */

	// Solution: A set of locations where antennas will be placed. S = {l_1, l_2, ... l_m} m -> max antennas placed ( number we're looking for ), l -> location of antenna.

	// Objective functions: min(f(S)) = |S|

	// Decisions: In which places should we place antennas? (Multi-way)

	/* Optimal Substructure:

	S* = (l_1*, l_2*, ..., l_m*)
	min(f(S*)) = |S*|

	Let's define S' = S* \ {l_1*}. In this case, |S*| = 1+|S''|

	Hypothesis: There exists a set S'' = {l_2', ..., l_m'} that is better than S'.

	This set will be part of S''', which will be |l_1*|+|S''|, which will essentially be 1+|S''|

	If S'' were more optimal than S', then |S'''| = 1+|S''| < 1+|S'| = |S*|

	However, we have found a contradiction, because |S'| is supposed to be a subset of the most optimal set,
	hence, 1+|S''| can't be greater than 1+|S'|!

	As such, we have demonstrated that there is no optimal subset by contradiction

	*/

	// Greedy components:

	// Initialization: S <- {N_1+C}

	// Completion: n <= l_m+C

	// Generation: Candidates(S) = G(l_last)

	// Selection: Select(S,C) = s_last+C

	// Extension: S <- S \cup {selected location}

	public Antennas(List<Integer> neighbourhood, int c) {
		km = neighbourhood;
		coverage=c;
		
	}

	private int nextneighborhood(int lastreached, List<Integer> antennaPositions){
		int i = lastreached;
		while (i < km.size() && km.get(i) <= antennaPositions.get(antennaPositions.size()-1)+coverage){
			i++;
		}
		return i;
	}
	
	public List<Integer> placeAntennas(){
		
		List<Integer> antennaPositions = new ArrayList<>();
		//****Complete your code here *****

		antennaPositions.add(km.get(0)+coverage);
		int lastreached = 1;

		lastreached = nextneighborhood(lastreached, antennaPositions);

		while( antennaPositions.get(antennaPositions.size()-1)+coverage <= km.get(km.size()-1) ){
			antennaPositions.add(km.get(lastreached)+coverage);
			lastreached = nextneighborhood(lastreached, antennaPositions);
		}

        return antennaPositions;
	}
	
	
}
