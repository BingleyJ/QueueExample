import java.util.Random;


public class Customer {
	int waitTime = 0;
	int itemsInBasket = 0;
	
	public Customer(int inItems){
		this.itemsInBasket = inItems;
	}
	
	public int getWaitTime() {
		return waitTime;
	}

	public void addMinuteWaitTime() {
		this.waitTime++;
	}
	
	public void killitem(){
		itemsInBasket--;
	}
	
	public boolean basketisempty(){
		if (itemsInBasket > 0)
			return false;
		else return true;
	}

}
