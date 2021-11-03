package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class TextAnalyzer extends Application {
	@Override
		public void start(Stage stage)
		{
		// Prepare Scene
		Scene scene = new Scene(new Group());TextField tb = new TextField();
			Label lbl = new Label();
			lbl.setLayoutX(300);
			lbl.setLayoutY(100);
			lbl.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 24));
		
		// Prepare Button
		Button btnStart = new Button("Start");
			btnStart.setLayoutX(120);
			btnStart.setOnAction((ActionEvent e) -> {
				try {
					TextAnalyzer();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}	              
			});
		VBox RBpane = new VBox();
		RBpane.setPadding(new Insets(10,10,10,10));
		RBpane.setSpacing(5);
		RBpane.getChildren().addAll(btnStart);

		// Prepare the Vertical Box
		VBox vbox = new VBox();
		vbox.setMaxWidth(700);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10,10, 10));
			vbox.getChildren().addAll(lbl, RBpane);
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		// Prepare the Stage
		stage.setScene(scene);
		stage.setTitle("Word Occurrences");
		stage.setWidth(700);
		stage.setHeight(550);
		stage.show();
		}

	private void TextAnalyzer() throws FileNotFoundException {
		File file = new File("src/textFile.txt");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		/**
		 * map to store key value pair
		 * key : word
		 * val: frequency of the word
		*/
		Map<String,Integer> map = new HashMap<String, Integer>(); 
		while (scan.hasNext())
		{
			String val = scan.next(); // read every word in the document
            if(map.containsKey(val) == false) // insert by setting the frequency as 1 if the string is not inserted in the map
                map.put(val,1);
            else // otherwise remove the entry from map and again insert by adding 1 in the frequency
            {
                int count = (int)(map.get(val)); // find the current frequency of the word
                map.remove(val);  // remove the entry from the map
                map.put(val,count+1); // reinsert the word and increase frequency by 1
            }
        }
        Set<Map.Entry<String, Integer>> set = map.entrySet(); // retrieve the map contents
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set); // make an array list of entries 
        Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() // sort the array list
        {
            public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // compare entries and sort them
            {
                return (b.getValue()).compareTo( a.getValue() ); // descending order 
             //	return (a.getValue()).compareTo( b.getValue() ); // ascending order 
            }
        } ); 
    
        // print the list
        List<Map.Entry<String, Integer>> sortedList2 = sortedList.subList(0, 20);

        for(Map.Entry i:sortedList2){
          
        System.out.println((i.getKey()+" -> "+i.getValue()));
        }

    }
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
}
