import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*this class creates arraylist of item object that represent the menu items 
   and contains all of the items in the file*/

public class Menu {
	private String name;

	private String catagory;

	private int price;

	private ArrayList <Item> arr = new ArrayList <> ();

	int cnt = 1;

	public Menu() {

		try {
			Scanner input = new Scanner(new File("Menu.txt"));
			//loop go over each 3 lines and creating item object and adding it to the menu array
			while (input.hasNext()){

				String st = input.next();

				if(cnt == 1) {

					name = st;

					cnt++;
				}

				else if(cnt == 2) {

					catagory = st;

					cnt++;
				}

				else if(cnt == 3) {

					price = Integer.parseInt(st); 	


					Item a = new Item(name, catagory, price);



					arr.add(a); 

					cnt = 1;


				}

			} 

			input.close(); 


		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String toString() {

		String s = "";

		for(int i = 0; i<arr.size(); i++)

			s += arr.get(i).toString()+"\n";

		return s;
	}

	public int size() {

		return arr.size();
	}


	public String getName(int i) {

		return arr.get(i).getName();
	}

	public String getCatagory(int i) {

		return arr.get(i).getCatagory();
	}

	public int getPrice(int i) {

		return arr.get(i).getPrice();
	}

}
