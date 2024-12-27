package sticks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
public class Config {
	private Player player1;
    private Player player2;
    private boolean attackPlayer1;
    static List<Config> taken =	new LinkedList<Config>();
    public Config(int _p11, int _p12, int _p21, int _p22, boolean lAttackPlayer1) {
        player1 = new Player(_p11,_p12);
        player2 = new Player(_p21,_p22);
        attackPlayer1 = lAttackPlayer1;

    }
    public Config() {
        player1 = new Player(1,1);
        player2 = new Player(1,1);
        attackPlayer1  = false;
    }
    // Get hands
    public Player getPlayer1() {
    	return player1;
    	
    }
    public Player getPlayer2() {
    	return player2;
    	
    }
    public int getHand1 () {
    	return player1.getH1();
    }
    public int getHand2 () {
    	return player1.getH2();
    }
    public int getHand3 () {
    	return player2.getH1();
    }
    public int getHand4 () {
    	return player2.getH2();
    }
    public boolean getAttack() {
    	return attackPlayer1;
    }
    // Get next possible movesj
    // Get- compare with another configuration
    // Probably Get- method that calls itself to get all next moves, loops through each possible
    /*
    public int[] getConfig() { // 
        int[] toReturn = {0};
        return toReturn;
    }
    public boolean isSame(Config _config) {
        return false;
    }
    */
    public void fight(boolean attackHand1, boolean withHand1) {
    	if (attackPlayer1 && attackHand1 && withHand1) player1.attack(getHand3(), true);
    	if (attackPlayer1 && attackHand1 && !withHand1) player1.attack(getHand4(), true);
    	if (attackPlayer1 && !attackHand1 && withHand1) player1.attack(getHand3(), false);
    	if (attackPlayer1 && !attackHand1 && !withHand1) player1.attack(getHand4(), false);
    	//
    	if (!attackPlayer1 && attackHand1 && withHand1) player2.attack(getHand1(), true);
    	if (!attackPlayer1 && attackHand1 && !withHand1) player2.attack(getHand2(), true);
    	if (!attackPlayer1 && !attackHand1 && withHand1) player2.attack(getHand1(), false);
    	if (!attackPlayer1 && !attackHand1 && !withHand1) player2.attack(getHand2(), false);
    }
    public static void reset() {
    	taken.clear();
    	
    }
    // public 
    public Config[][] getOutcomes(Config[] thisOutcome) { // Method which creates array of outcomes
    	taken.add(this);
    	// System.out.println(getHand1() + ", " + getHand2() + ", " + getHand3() + ", " + getHand4());
    	List<Config> thisOutcomeList =	new LinkedList<Config>(Arrays.asList(thisOutcome));
    	thisOutcomeList.add(this);
    	thisOutcome = new Config[thisOutcomeList.size()];
    	// System.out.println(thisOutcomeList.size());
    	thisOutcome =  thisOutcomeList.toArray(thisOutcome);//new Config[thisOutcomeList.size()];
    	
    	ArrayList<ArrayList<Config>> outcomesList = new ArrayList<ArrayList<Config>>();
    	Config[] mThisOutcome = new Config[outcomesList.size()];
    	mThisOutcome = outcomesList.toArray(mThisOutcome);
    	List<Config[]> outcomes = new ArrayList<Config[]>();
    	if (getHand1() + getHand2() == 0) return new Config[][] {};
    	else if (getHand3() + getHand4() == 0) {
    		//System.out.println("done");
    		return new Config[][] {thisOutcome};
    		
    	}
    	// if not last one
    	ArrayList<Config> configs = new ArrayList<Config>();

    	if (attackPlayer1) {
        for (int i=0; i<4+player2.getFingers();i++) {
        		configs.add(new Config(getHand1(), getHand2(), getHand3(), getHand4(),!attackPlayer1));
        }
    	configs.get(0).fight(true,true);
    	configs.get(1).fight(false,true);
    	configs.get(2).fight(true,false);
    	configs.get(3).fight(false,false);
    	for (int i=1; i<=getHand3();i++) {
    		configs.get(3+i).getPlayer2().swap(i,true);
    	}
    	for (int j=1; j<=getHand4();j++) {
    		configs.get(3+j).getPlayer2().swap(j,false);
    	}
    	} else {
        for (int i=0; i<4+player1.getFingers();i++) {
        		configs.add(new Config(getHand1(), getHand2(), getHand3(), getHand4(), !attackPlayer1));
        }
    	configs.get(0).fight(true,true);
    	configs.get(1).fight(false,true);
    	configs.get(2).fight(true,false);
    	configs.get(3).fight(false,false);
    	for (int i=1; i<=getHand1();i++) {
    		configs.get(3+i).getPlayer1().swap(i,true);
    	}
    	for (int j=1; j<=getHand2();j++) {
    		configs.get(3+j).getPlayer1().swap(j,false);
    	}
    	}

    	for (Config j: configs) {
    		//System.out.println("To be checked: " + j.getHand1() + ", " + j.getHand2() + ", " + j.getHand3() + ", " + j.getHand4());
    		boolean isTaken = false;
    		for (Config a: taken) {
    		if ((a.getHand1() + a.getHand2() == j.getHand1() + j.getHand2() && (a.getHand1() == j.getHand1() || a.getHand2() == j.getHand1())) && (a.getHand3() + a.getHand4() == j.getHand3() + j.getHand4() && (a.getHand3() == j.getHand3() || a.getHand4() == j.getHand3())) && a.getAttack() == j.getAttack()) {
    			isTaken = true;
    			break;
    		}
    		}
    		if (!isTaken) {	

    		List<Config[]> outcomesBelow = Arrays.asList(j.getOutcomes(thisOutcome));
    		for (Config[] k: outcomesBelow) {
    			outcomes.add(k); // add all config[] from outcomesBelow
    			 // System.out.println(k[0].getHand1());
    		
    		}
    		}
    	}
    	
    	// int attacks = 4
    	// after each, if one has won, return full config to be added to outcomes-- return as config[][], 
    	// if not, continue by calling getOutcomes; 
    	// attacks, then switches
    	Config[][] outcomesArray = new Config[outcomes.size()][];

    	outcomesArray = outcomes.toArray(outcomesArray);
    	// System.out.println(outcomesArray[0][0].getHand1());
    	return outcomesArray;
    } 
    // public boolean compare (Config _config) {
    //     return 
    // }
}
