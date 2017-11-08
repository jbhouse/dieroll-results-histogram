import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Histogram {

	private int[] results;
	private int[] resultsTally;
	private int maxResult;
	private int numberOfDie;

	public Histogram(int[] results) {
		this.results=results;
	}

	public int[] getResults() {
		return results;
	}
	
	public int getNumberOfDie() {
		return numberOfDie;
	}

	public void setNumberOfDie(int numberOfDie) {
		this.numberOfDie = numberOfDie;
	}
	
	public void display(int[] results) {
// take our this.resultstally and create a columns hash in order to display the histogram
		HashMap<Integer, List<String>> hashOfColumns = new HashMap<Integer, List<String>>();
// the reason i use getMaxresults+2 is because we have to add +1 for starting at 0. 
// and another +1 to give us an extra column for displaying the total above the #'s 
		int height = results[0];

		for (int i = 1; i < this.resultsTally.length; i++) {
		    if (this.resultsTally[i] > height) {
		      height = this.resultsTally[i];
		    }
		}
		for (int i = 0; i < height+1; i++) {
			hashOfColumns.put(i, new ArrayList<String>());
		}
		
		for (int i = 0; i < height+1; i++) {
			int colheight = height-i;
			for (int j = 0; j < this.resultsTally.length; j++) {
				if (this.resultsTally[j]>colheight) {
					hashOfColumns.get(i).add("#  ");
				} else if (this.resultsTally[j]<colheight) {
					hashOfColumns.get(i).add("   ");
				} else {
					hashOfColumns.get(i).add(String.valueOf(this.resultsTally[j]));
					if (this.resultsTally[j]<10) {
						hashOfColumns.get(i).add("  ");
					} else if (this.resultsTally[j]<100) {
						hashOfColumns.get(i).add(" ");
					}
				}
			};
			if (i==height) {
//				if we are on the last column, an empty value came up as 0, not a " ". so we just go 
//				through and replace 0's with " "'s before displaying
				StringBuilder ns = new StringBuilder(String.join("", hashOfColumns.get(i)));
				for (int j = 0; j < ns.length(); j++) {
					if (ns.charAt(j)=='0') {
						ns.deleteCharAt(j);
						ns.insert(j, " ");
					}
				}
				System.out.println(ns.toString());
			} else {
				System.out.println(String.join("", hashOfColumns.get(i)));
			}
			
		};
		StringBuilder sb= new StringBuilder();
		StringBuilder nb= new StringBuilder();
		StringBuilder mb= new StringBuilder();
		for(int i= 0 ; i <= (this.getMaxResult()+1)*3 ; i++ ) {
			if (i<numberOfDie*3) {
				sb.append( " " );
			} else {
				sb.append( "-" );
			}
		};
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		for(int j=numberOfDie; j<=this.getMaxResult(); j++) {
			nb.append(String.valueOf(j));
			if (j<10) {
				nb.append("  ");
			} else if (j<100) {
				nb.append(" ");
			}
		};
		for (int i = 0; i < numberOfDie; i++) {
			mb.append("   ");
		}
		System.out.println(sb.toString());
		System.out.println(mb.toString()+nb.toString());
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int[] results) {
		int max = results[0];

		for (int i = 1; i < results.length; i++) {
		    if (results[i] > max) {
		      max = results[i];
		    }
		}
		this.maxResult = max;
	}

	public int[] getResultsTally() {
		return resultsTally;
	}

	public void setResultsTally(int[] results) {
		this.resultsTally = new int[this.getMaxResult()+1];
		for (int i = 0; i < results.length; i++) {
			this.resultsTally[results[i]]++;
		}
	}


	
//	public void setResults(HashMap<String, Object> args) {
//		if (args) {
//			if our args contains the piece of information we are looking for
//			size, weight, etc. then we create an instance variable out of it
//		}
//	}
	
}
