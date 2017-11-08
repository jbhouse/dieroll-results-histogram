import java.util.*;

public class Result {
	
	public static String askToContinue(Scanner sc) {
        System.out.print("Continue? (y/n): ");        
        String validInput = "yn";
        while (true) {
        	String inputChecker = sc.next();
            if (!validInput.contains(inputChecker)) {
    			System.out.println("please enter \"y\" or \"n\"");
    			continue;
    		} else {
    	        sc.nextLine();
    	        return inputChecker;
    		}
		}
    }
	
	public static String histogram(final int[] results) {
	    int[] sorted = new int[results.length];
	    for(int q=0; q<results.length; q++) {
	    	sorted[q] = results[q];
	    };
	    Arrays.sort(sorted); 
	    int largest = sorted[sorted.length-1];
//	    we need to create x+1 rows where x is the largest integer result from the die roll
//	    that way we can display the number, and the corresponding amount of hashes below 
	    
//	    each rows key value pairs is the integer id of the row, and an array we can add values into depending on
//	    the value of the die roll result being represented in that column in the array
	    HashMap<Integer, List<String>> hashOfRows = new HashMap<Integer, List<String>>();
	    for(int j=0; j<largest+1; j++){
	      hashOfRows.put(j, new ArrayList<String>());
	    };
//	    here we go through each row in turn(each key), analyzing each column in the array(each value) in turn, 
//	    and determining if the number that correlates to said column is lower, higher, or the same as, 
//	    the height of the current column then we add a symbol to said array based on that information
	    for(int k=0; k<=largest; k++) {
	    	int colheight = largest-k;
	    	for(int h=0; h<results.length; h++) {
	    		if (results[h]>colheight) {
	    			hashOfRows.get(k).add("#   ");
				} else if (results[h]<colheight) {
					hashOfRows.get(k).add("    ");
				} else {
//					if the height of the row is equal to the result of the die roll, we check the result of the die roll
//					and append differing values to our array depending on the size of said number, in order to assure consistent formatting
					hashOfRows.get(k).add(String.valueOf(results[h]));
					if (results[h]<10) {
						hashOfRows.get(k).add("   ");
					} else if (results[h]<100) {
						hashOfRows.get(k).add("  ");
					} else {
						hashOfRows.get(k).add(" ");
					}
				}
	    	};
	    	System.out.println(String.join("", hashOfRows.get(k)));
	    };
	    return "fail message";
	};
	
	public static int askForInput(Scanner sc, String message) {
		System.out.print(message);
		while (true) {
			if (sc.hasNextInt()) {
				return sc.nextInt();
			} else {
				System.out.println("Please enter a valid number");
				continue;
			}
		}
	};
	
	public static int rollDie(int times,int sides) {
		int total = 0;
		for (int i = 0; i < times; i++) {
			total += Dice.rollDie(sides);
		}
		return total;
	};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			int dice = askForInput(sc, "How many dice would you like to roll? : ");
			int sides = askForInput(sc, "How many sides should each dice have? : ");
			int rolls = askForInput(sc, "How many times would you like to roll the die? : ");
			int[] results = new int[rolls];
			for (int i = 0; i < rolls; i++) {
				results[i] = rollDie(dice,sides);			
			}
			histogram(results);
			StringBuilder sb= new StringBuilder();
			for(int i= 0 ; i < results.length+((results.length-1)*3) ; i++ ) sb.append( "-" ); 
			System.out.println(sb.toString());
			StringBuilder nb= new StringBuilder();
			for(int j=1; j<=results.length; j++) {
				nb.append(String.valueOf(j));
				if (j<10) {
					nb.append("   ");
				} else if (j<100) {
					nb.append("  ");
				} else {
					nb.append(" ");
				}
			};
			System.out.println(nb.toString());
			System.out.println();
			Histogram newHistogram = new Histogram(results);
			newHistogram.setNumberOfDie(dice);
			newHistogram.setMaxResult(results);
			newHistogram.setResultsTally(results);
			newHistogram.display(results);
			choice = askToContinue(sc);
		}
		sc.close();
		System.out.println("Bye for now, please come and play again.");
	}

}
