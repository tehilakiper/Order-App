import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//this class create object of item information

public class Item {

	private String name;

	private String catagory;

	private int price;


	public Item (String name, String catagory, int price) {

		this.name = name;

		this.catagory = catagory;

		this.price = price;
	}


	public String toString() {

		return name + "\n" + catagory + "\n" + price + "\n"; 
	}

	public String getName() {

		return name;
	}

	public String getCatagory() {

		return catagory;


	}

	public int getPrice() {

		return price;
	}




}
