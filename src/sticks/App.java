package sticks;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class App {

	public static void main(String[] args) {

		final String LINE = "|";
		Scanner keys = new Scanner(System.in); // Import scanner
		while (true) {
		boolean lines = false;
        System.out.print("Enter sticks configuration in 'firstPlayerOneHand,secondPlayerOneHand,firstPlayerTwoHand,secondPlayerTwoHand' followed by 'stick' or 'num' For example: 1,2,1,4 num"); // Ask player
        String inConfig = keys.nextLine(); //Receive player
        Config firstConfig = new Config();
        try {
            firstConfig = new Config(Integer.parseInt(inConfig.substring(0,1)),Integer.parseInt(inConfig.substring(2,3)),Integer.parseInt(inConfig.substring(4,5)),Integer.parseInt(inConfig.substring(6,7)), false); // Parse input, create new config
            if (inConfig.length() > 8 && inConfig.substring(8).equals("stick")) {
            	lines = true;
            } else if (inConfig.length() > 8 && inConfig.substring(8).equals("num")) {
            	lines = false;
            }
            
        } catch (Exception e) {
            firstConfig = new Config();// If cannot be parsed, create config with 1,1 on left and 1,1 on right, use numbers
            
        } finally {
        // ask config for all good outcomes, as two-dimensional array
        Config[][] outcomes = firstConfig.getOutcomes(new Config[]{});
        String allOutcomes = "";
        // loop through array of arrays
        for (Config[] i: outcomes) {
        	String outcome = "";
        	int ind = 0;
        	for (Config j: i) {
        		if (lines) {

        			outcome += LINE.repeat(j.getHand1()) + ", " + LINE.repeat(j.getHand2()) + " vs " + LINE.repeat(j.getHand3()) + ", " + LINE.repeat(j.getHand4());
        		}
        		else {
        			outcome += j.getHand1() + ", " + j.getHand2() + " vs " + j.getHand3() + ", " + j.getHand4();
        		}
        		ind++;
        		if (i.length != ind) {
        			outcome += " => ";
        		}
        		
        	}
        	System.out.println(outcome);
        	allOutcomes += outcome + "\n\n";
        } 
        
        // 	loop through array
        // 	if type == "sticks",  "|" * number of fingers + ",", all four hands, then "=>", for as many configurations
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection outcomesSelection = new StringSelection(allOutcomes);
        clipboard.setContents(outcomesSelection, null);
        System.out.println("Copied to clipboard");
        }
        Config.reset();
		}
		

	}

}
