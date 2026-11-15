
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SubsetSumDifference {
	
	/**
	 * 
	 * @param l list of natural numbers (input)
	 * @param k  size of one of the subset (input)
	 * @param s1  subset 1 (output)
	 * @param s2  subset 2 (output)
	 * @return  maximised difference between the two subsets
	 * 
	 */
	public static int maximise(List<Integer> l, int k, List<Integer> s1, List<Integer> s2) {

		Collections.sort(l);

		int sums1 = 0; int sums2 = 0;

		if (k <= l.size()/2){
			for (int i = 0; i < l.size(); i++) {
				if (i < k){
					s1.add(l.get(i));
					sums1 += l.get(i);
				} else {
					s2.add(l.get(i));
					sums2 += l.get(i);
				}
			}
		} else {
			for (int i = 0; i < l.size(); i++) {
				if (i < k){
					s1.add(l.get(l.size()-i-1));
					sums1 += l.get(l.size()-i-1);
				} else {
					s2.add(l.get(l.size()-i-1));
					sums2 += l.get(l.size()-i-1);
				}
			}
		}
	 	int summ = Math.abs(sums2 - sums1);

		return summ;
    }
	
}
