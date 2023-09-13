import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainController {

	@FXML
	private VBox V;


	@FXML
	private GridPane grid1;

	@FXML
	private GridPane grid2;

	@FXML
	private GridPane grid3;

	@FXML
	private GridPane grid4;

	@FXML
	private Button orderbtn;

	private Menu a = new Menu ();

	private TextArea b = new TextArea();

	private int cnt1 = 0, cnt2 = 0 , cnt3 = 0, cnt4 = 0;

	private Order [] Receipt = new Order[100];

	private int price;


	@FXML

	public void initialize() {

		setMenu();



	}
	//set combobox with numbers
	public void initiateComboBox(ComboBox co) {

		for(int i = 0; i<=100; i++) {

			co.getItems().add(i+"");
		}
	}
	//set the menu layout and options
	private void setMenu() {

		for(int i = 0; i < a.size(); i++) {

			CheckBox ch = new CheckBox();

			ComboBox co = new ComboBox();

			co.setValue(""+0);

			initiateComboBox(co);

			String name = a.getName(i);
			int price = a.getPrice(i);
			int val = i; 
			String l = "";
			//rewrite the item name with spaces

			for(int j =0; j<name.length(); j++) {

				if(name.charAt(j) == '-' )

					l += " ";
				else

					l += name.charAt(j);
			}
			//write the item name and price in the menu as labels

			Label label1 = new Label(l);

			Label label3 = new Label(a.getPrice(i)+"");

			System.out.println(label1.getText());

			//adding to the right place in the menu by the category

			if(a.getCatagory(i).equals("FirstCourse")) {

				grid1.addRow(cnt1, ch, co, label1, label3);

				cnt1++;
			}


			if(a.getCatagory(i).equals("MainCourse")) {

				grid2.addRow(cnt2, ch, co, label1, label3);

				cnt2++;
			}

			if(a.getCatagory(i).equals("Dessert")) {

				grid3.addRow(cnt3, ch, co, label1, label3);

				cnt3++;


			}

			if(a.getCatagory(i).equals("Bavarage")) {

				grid4.addRow(cnt4, ch, co, label1, label3);

				cnt4++;
			}


			//in case the checkbox in clicked	
			ch.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {


					handleCheckBoxAction(event);

				}

				private void handleCheckBoxAction(ActionEvent event) {

					CheckBox temp = (CheckBox)event.getSource();

					//add item to an array of "Order"
					if(temp.isSelected()) {

						int b = Integer.parseInt((String)co.getValue());

						Order a = new Order (b, name, price);

						Receipt[val] = a;

					}

					//remove item if it's unclicked

					if(!temp.isSelected())

						Receipt[val] = null;	




				}

			});
			//updates number of products in case number in the combobox is changed
			co.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {


					handleCheckBoxAction(event);

				}

				private void handleCheckBoxAction(ActionEvent event) {

					ComboBox temp = (ComboBox)event.getSource();

					if(Receipt[val] != null) {

						int b = Integer.parseInt((String)co.getValue());

						Receipt[val].setSumOfProducts(b);
					}
				}


			});
		}

	}


	@FXML
	void orderbtn(ActionEvent event) {

		int buyTotalPrice = 0;

		String s ="you orderd: \n";
		//loop finds the buy total price
		for(int i = 0; i< 100; i++) {

			if(Receipt[i] != null) {

				s += Receipt[i].toString() + "\n";

				buyTotalPrice += Receipt[i].getTotalSum();
			}


		}
		//dialog box options		

		s = s +"\ntotal to pay: "+ buyTotalPrice;


		String [] options = { "Confirm Order", "Continue Order", "Cancel Order"};

		int choice = JOptionPane.showOptionDialog(null, s, "Chek out", 0, 3, null, options, options[0]); 

		if(choice == JOptionPane.OK_OPTION) {

			String nameAndId = JOptionPane.showInputDialog("enter your first name and ID ");

			try {

				FileWriter writer = new FileWriter(nameAndId+".txt");

				writer.write(nameAndId);
				writer.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
			//keep buying
			cancel();
		}

		if(choice == JOptionPane.CANCEL_OPTION) {

			cancel();
		}
	}
	//reset the buy
	private void cancel() {

		grid1.getChildren().clear();

		grid2.getChildren().clear();

		grid3.getChildren().clear();

		grid4.getChildren().clear();

		Receipt = new Order[100];

		initialize();

	}
}
