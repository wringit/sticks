package sticks;

public class Player {
    int h1;
    int h2;
    public Player (int lH1, int lH2) {
        h1 = lH1;
        h2 = lH2;
    }
    // get- internal moves possible
    // get- fingers on each hand
    public int getH1 (){
        return h1;
    }
    public int getH2 (){
        return h2;
    }
    // set- attack
    public void attack(int amount, boolean is1) {
        if (is1 == true) {
            h1 += amount;
        } else {
            h2 += amount;
        }
        h1 %= 5;
        h2 %= 5;
    }
    public int getFingers() {
    	return h1+h2;
    }
    // set- swap
    public void swap (int amount, boolean take1) {
    	// transfer amount to other hand
    	int fingers;
    	if (take1) fingers = h1;
    	else fingers = h2;
    	if (amount > fingers) {
    		return;
    	}
    	if (take1 ) {
    		h1 -= amount;
    		h2 += amount;
    	} else {
    		h2 -= amount;
    		h1 += amount;
    	}
        h1 %= 5;
        h2 %= 5;
    }
}
