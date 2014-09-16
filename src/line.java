import java.util.ArrayList;
import java.util.Random;


public class line {
	ArrayList<Customer> lineOfCustomers = new ArrayList<Customer>();
	
	public line(){
		
	}
	
	public void addCustomers(int inCustomers, int inMaxItems){
		for (int i = 0; i < inCustomers; i++){
			Customer customer = new Customer(randInt(0, inMaxItems));
			lineOfCustomers.add(customer);
		}
	}


	public void serviceNextCustomer(int totalCashiersIn){
		//kill next customer
		if (!lineOfCustomers.isEmpty()){
			for (int i = 0; i < totalCashiersIn; i++){
				if (!lineOfCustomers.isEmpty())
					lineOfCustomers.remove(0);
			}
		}
	}
	
	public void update() {
		//add wait time to each customer in line
		for (int i = 0; i < lineOfCustomers.size(); i++){
			Customer customer = lineOfCustomers.get(i);
			customer.addMinuteWaitTime();
		}
	}

	public int checkForRecordWaitTime(int maxWaitTime) {
		int tempMax = maxWaitTime;
		for (int i = 0; i < lineOfCustomers.size(); i++){
			Customer customer = lineOfCustomers.get(i);
			if (customer.getWaitTime() > maxWaitTime)
				tempMax = customer.getWaitTime();
		}
		return tempMax;
	}

	public int addTotalWait(int totalWaitTime) {
		int tempMax = totalWaitTime;
		for (int i = 0; i < lineOfCustomers.size(); i++){
			Customer customer = lineOfCustomers.get(i);
			tempMax += customer.getWaitTime();
		}
		return tempMax;
	}


	public int length() { 
		return this.lineOfCustomers.size();
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
