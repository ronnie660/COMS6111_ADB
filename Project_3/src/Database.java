import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Database {
	private ArrayList<ArrayList<String>> table;
	private ArrayList<Set<String>> checking;
	private double support;
	private double confidence;
	private Set<String> itemset;	// store all items from table
	
	public Database(ArrayList<ArrayList<String>> table, double support, double confidence) {
		this.table = new ArrayList<ArrayList<String>>(table);
		this.support = support;
		this.confidence = confidence;
		checking = new ArrayList<Set<String>>();
		itemset = new HashSet<String>();
		
		/*
		 * After getting the database, generate a item set for future use,
		 * and also store each row as a set.
		 */
		for (ArrayList<String> items : this.table) {
			Set<String> temp = new HashSet<String>();
			for (String item : items) {
				itemset.add(item);
				temp.add(item);
			}
			checking.add(temp);
		}
	}
	
	public double count(ArrayList<String> s) {
		Set<String> a = new HashSet<String>();
		for (String t : s) 
			a.add(t);
		double c = 0;
		for (Set<String> itemset : checking)
			if(itemset.containsAll(a)) c++;
		c = c / table.size();
		return c;
	}
	
	public Lt generate_Lt(ArrayList<ArrayList<String>> candidates) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<Double> supports = new ArrayList<Double>();
		for (ArrayList<String> item : candidates) {
			double c = count(item);
			/* Greater then or equal to */
			if (c >= support) {
				res.add(item);
				supports.add(c);
			}
		}
		return new Lt(res , supports);
		
	}
	
}
