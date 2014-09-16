import java.util.Random;

/*	John Bingley - Queue Homework
 *  Data Structures - Lonnie Bowe
 *  
 *  Down and dirty Queue assignment. No bells, no whistles.
 * 
 * Play with customizable variables for all types of scenarios
 * 
 * I already see some serious refactoring that could be done
 * 1) update function could do a lot more to lower overhead as a whole
 * 2) With more time, a gui would be nicer.
 */



public class StoreQueueExample {
	//-----------customizable variables-----------------------------------------------|
	private static int runtime = 120; //fake minutes			
	private static int totalQueues = 1; //total lines
	private static int totalCashiers = 1; //total cashiers
	private static int totalPossibleItemsInBasket = 4; //for added wait times
	private static int maxPossibleCustomersAddedPerMinute = 2; //people entering line
	//--------------------------------------------------------------------------------|
	private static line[] lines;
	private static int customersServed = 0;
	private static int originalRuntime = runtime;
	private static int maxWaitTime = 0;
	private static int totalWaitTime = 0;
	
	public static void main(String[] args) {
		lines = new line[totalQueues];
		for(int i = 0; i < totalQueues; i++){
			line Line = new line();
			lines[i] = Line;
		}
		
		while(runtime > 0){
			
			int newCustomers = randInt(0, maxPossibleCustomersAddedPerMinute);
			
			//add customers to shortest lines
			for (int i = 0; i < newCustomers; i++) {
				int templine = checkForShortestLine();
				lines[templine].addCustomers(1, totalPossibleItemsInBasket);
			}
			//add minute of wait time to everyone in line.
			for(int i = 0; i < totalQueues; i++){
				lines[i].update();
			}
			for(int i = 0; i < totalQueues; i++){
				totalWaitTime = lines[i].addTotalWait(totalWaitTime);
			}
			for(int i = 0; i < totalQueues; i++){
				maxWaitTime = lines[i].checkForRecordWaitTime(maxWaitTime);
			}
			//kill customers based on amount of cashiers.
			for(int i = 0; i < totalQueues; i++){
				lines[i].serviceNextCustomer(totalCashiers);
			}
			customersServed += totalCashiers;
			runtime--;
		}	
		System.out.println("---------------------------------------");
		System.out.println("After running for " + originalRuntime + " minutes.");
		System.out.println("Total Queues = " + totalQueues);
		System.out.println("Total Cashiers = " + totalCashiers);
		System.out.println("Customers Served = " + customersServed);
		System.out.println("Max wait Time = " + maxWaitTime);
		System.out.println("Avg wait Time = " + totalWaitTime / customersServed);
		System.out.println("Total wait time = " + totalWaitTime + " \n -Includes people left in line.");
		System.out.println("People still in line = " + getpeopleinline());
		System.out.println("---------------------------------------");

	}
	
	private static int getpeopleinline() {
		int peopleInLine = 0;
		for (int i = 0; i < totalQueues; i++){
			peopleInLine += lines[i].length();
		}
		return peopleInLine;
	}

	private static int checkForShortestLine() {
		int shortestline = 0;
		for(int i = 0; i < totalQueues; i++){
			if (lines[i].length() < lines[shortestline].length())
				shortestline = i;
		}
		return shortestline;
	}

		public static int randInt(int min, int max) {
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
}
