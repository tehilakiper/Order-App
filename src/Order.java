import java.util.ArrayList;

//this class represents the item added to the buy - its name, sum of it and its total cost

public class Order {

	private int totalSum;

	private int sumOfProducts;
	
	private String name;
	
	private int price;
	
	private ArrayList <Order> check = new  ArrayList <> ();

	public Order( int sumOfProducts, String name, int price) {
		
		this.totalSum = sumOfProducts*price; 
		
		this.price = price;
		
		this.name = name;
		
		this.sumOfProducts = sumOfProducts;
		
	}
	
	public String toString () {
		
		return  ""+ name + ", sum Of Items: " + sumOfProducts +", price: " +price +",total cost: "+ totalSum;
	}
	
	public int getTotalSum() {
		
		return totalSum;
	}
	
	public void setSumOfProducts(int val) {
		
		sumOfProducts = val;
		
		this.totalSum = sumOfProducts*price;
	}
	
}
