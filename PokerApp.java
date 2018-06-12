import java.util.*;

class PokerApp {
	public static void main(String[] args) {
		
		String done = " "; // Needed for do, while loop for the play again feature
		String[] black = new String[5];
		String[] white = new String[5];
		int card, suitRand;
		int counter;
		int wCounter;
		int wScore, wHigh = 0;
		int bScore, bHigh = 0;
		int wHiPair = 0;
		int bHiPair = 0;
		String bResult = " ", wResult = " ";
		char suitFinal = 'U', faceCard = 'U'; //initialized by setting to U for undefined
		char temp = ' ';
		int[] bHand = new int[5];
		int[] wHand = new int[5];
		char[] bSuit = new char[5];
		char[] wSuit = new char[5];
		
// play again do while loop	
	do { 	
	
		counter = 0; 
		wCounter = 0;
		//setting the count variables to zero allows them to be used correctly each time
		
// Game Intro Section
		System.out.println("Welcome to the poker: texas holdem. press enter to deal the cards.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine(); 
//Begins when user keys enter

//Random number generator to deal both hands.  The Hands are then converted using a switch statement to face cards if they are between (10 - 14)	
	do {
		Random rand = new Random();
		
		card = 2 + rand.nextInt(13); //This genrates a number between 2 and 14 because no 1 card
		suitRand = 1 + rand.nextInt(4); //This generates a random number between 1 and 4
		
		if(card > 9) {
			switch(card) {
				case 10: 
					faceCard = 'T';
					break;
				case 11: 
					faceCard = 'J';
					break;
				case 12:
					faceCard = 'Q';
					break;
				case 13: 
					faceCard = 'K';
					break;
				case 14: 
					faceCard = 'A';
					break;
			}		
		} 
//The random number is then converted to the character assigned for each suit		
		switch(suitRand) {
			case 1: 
				suitFinal = 'C';
				break;
			case 2:
				suitFinal = 'S';
				break;
			case 3: 
				suitFinal = 'D';
				break;
			case 4:
				suitFinal = 'H';
				break;
		}
		
//Display method returns a string that uses string builder to make a string ("card/suit")
		if (counter < 5) {
			 black[counter] = display(card, suitFinal, faceCard);
		} else {
			white[wCounter] = display(card, suitFinal, faceCard);
			wCounter++;
		}
		
		counter ++;
		
	} while(counter != 10);
	//These strings were used to test the program for specific hands and bypass the random option.  Feel free to uncomment and test different hands easier. 
	/*
		black[0] = "KC";
		black[1] = "QD";
		black[2] = "3D";
		black[3] = "4D";
		black[4] = "8D";	
		
		white[0] = "KC";
		white[1] = "QD";
		white[2] = "3D";
		white[3] = "4D";
		white[4] = "8D";	
	*/
//Formats each sting to the output that was given in the example. 
		System.out.format("Black: %s, %s, %s, %s, %s", black[0], black[1], black[2], black[3], black[4]);
		System.out.format("\tWhite: %s, %s, %s, %s, %s \n", white[0], white[1], white[2], white[3], white[4]);
	
// Black setup: converts facecards back to numbers. The numbers are then sorted to make testing hand results much easier.
	for(int i = 0; i < black.length; i++) {
		temp = black[i].charAt(0);
		
		if(temp == 'T') {
			bHand[i] = 10; 
		}
		else if (temp == 'J') {
			bHand[i] = 11; 
		}
		else if (temp == 'Q') {
			bHand[i] = 12; 
		}
		else if (temp == 'K') {
			bHand[i] = 13; 
		}
		else if (temp == 'A') {
			bHand[i] = 14; 
		} else {
			bHand[i] = Character.getNumericValue(temp); //If not facecard character is converted to numberical value
		}		
	}
	Arrays.sort(bHand); //sort function
	
	for(int i = 0; i < black.length; i++) {
			bSuit[i] = black[i].charAt(1);
	}
//white setup: same as black. There are two arrays now with just card numbers and two with just suits.
	
	for(int i = 0; i < white.length; i++) {
			temp = white[i].charAt(0);
			
			if(temp == 'T') {
				wHand[i] = 10; 
			}
			else if (temp == 'J') {
				wHand[i] = 11; 
			}
			else if (temp == 'Q') {
				wHand[i] = 12; 
			}
			else if (temp == 'K') {
				wHand[i] = 13; 
			}
			else if (temp == 'A') {
				wHand[i] = 14; 
			} else {
				wHand[i] = Character.getNumericValue(temp);
			}		
		}
		Arrays.sort(wHand);
		
		for(int i = 0; i < white.length; i++) {
				wSuit[i] = white[i].charAt(1);
		}	
	
	
	
//Black Scores:  Results are tested from rarest to most common.  They are assigned a score and a sting value making it easy to compare white and black hands and then taking the result string and displaying it to the end user. 
	
	if(straight(bHand) == true && flush(bSuit) == true) {
	 	bScore = 9; 
	 	bResult = "Straight Flush";
	} else if(fourOfAKind(bHand) == true) {
		bScore = 8;
		bResult = "Four Of A Kind";
	} else if (fullHouse(bHand) == true) {
		bScore = 7;
		bResult = "Full House";
	} else if(straight(bHand) == false && flush(bSuit) == true) {
		bScore = 6;
		bResult = "Flush";
	} else if(straight(bHand) == true) {
		bScore = 5;
		bResult = "Straight";
	} else if(threeOfAKind(bHand) == true && fullHouse(bHand) == false && fourOfAKind(bHand) == false) {
		bScore = 4;
		bResult = "Three Of A Kind";
	} else if(twoPair(bHand) == true) {
		bScore = 3;
		bResult = "Two Pair";
	} else if(pair(bHand) == true) {
		bScore = 2;
		bResult = "Pair";
	} else if(hiCard(bHand) == true) {
		bScore = 1;
		bResult = "High Card";
		bHigh = bHand[4];
	} else {
		bScore = 0;
	}
	
//White Scores
	
		if(straight(wHand) == true && flush(wSuit) == true) {
		 	wScore = 9; 
		 	wResult = "Straight Flush";
		} else if(fourOfAKind(wHand) == true) {
			wScore = 8;
			wResult = "Four Of A Kind";
		} else if (fullHouse(wHand) == true) {
			wScore = 7;
			wResult = "Full House";
		} else if(straight(wHand) == false && flush(wSuit) == true) {
			wScore = 6;
			wResult = "Flush";
		} else if(straight(wHand) == true && flush(wSuit) == false) {
			wScore = 5;
			wResult = "Straight";
		} else if(threeOfAKind(wHand) == true && fullHouse(wHand) == false && fourOfAKind(wHand) == false) {
			wScore = 4;
			wResult = "Three Of A Kind";
		} else if(twoPair(wHand) == true) {
			wScore = 3;
			wResult = "Two Pair";
		} else if(pair(wHand) == true) {
			wScore = 2;
			wResult = "Pair";
		} else if(hiCard(wHand) == true) {
			wScore = 1;
			wResult = "High Card";
			wHigh = wHand[4];
		} else {
			wScore = 0;
		}

//Who won? this section displays who won and with what hand or if they have tied.
	if(bScore < wScore) {
		System.out.println("White Wins with: " + wResult);
	} else if(bScore > wScore) {
		System.out.println("Black Wins with: " + bResult);
	} else if(bScore == wScore && (wScore != 1 && bScore != 1)) {
		System.out.println("Tie: " + wResult);
	} else {
		// This loop is necessary if the high card is the same for both hands. 	It then compares the second highest and so on.  If it gets to the last element of the arrays to compare a. message is displayed about how rare it is! 
		int k = 4;
		while(wHigh == bHigh) {
			if(bHand[k] == wHand[k]) {
				wHigh = wHand[k-1];
				bHigh = bHand[k-1];
				k--;
			}
			if(k == 0 && bHand[k] == wHand[k]){
				System.out.println("Wow the hands are exactly the same.  The odds of that is crazy. Please restart the game.");
			}
		}
// Convert facecards back to a character so that they can be displayed like the example
	 	if(wHigh < bHigh){
				if(bHigh > 9) {
					switch(bHigh) {
						case 10: 
							temp = 'T';
							break;
						case 11: 
							temp = 'J';
							break;
						case 12:
							temp = 'Q';
							break;
						case 13: 
							temp = 'K';
							break;
						case 14: 
							temp = 'A';
							break;
						}		
				System.out.println("Black wins with: " + wResult + " - " + temp);
				} else {
				System.out.println("Black wins with: " + wResult + " - " + bHigh);
				}
		} else if(bHigh < wHigh){
				if(9 < wHigh) {
					switch(wHigh) {
						case 10: 
							temp = 'T';
							break;
						case 11: 
							temp = 'J';
							break;
						case 12:
							temp = 'Q';
							break;
						case 13: 
							temp = 'K';
							break;
						case 14: 
							temp = 'A';
							break;
						}			
					System.out.println("White wins with: " + wResult + " - " + temp);
				} else {
					System.out.println("White wins with: " + wResult + " - " + wHigh);
				}
		}	
	 }
//Allows the user to run the program again with new cards or type "done" which ends the gam	
	System.out.println("\nPlay again? Type 'done' if finished");
	done = scan.nextLine().toLowerCase();
	}while(!"done".equals(done));
	
//End Main method Bracket
	}
	
//Methods 	
	public static String display(int card, char suit, char faceCard) {
		StringBuilder sb = new StringBuilder();
		
		if(card < 10) {
			return sb.append(card).append(suit).toString();
		} else {
			return sb.append(faceCard).append(suit).toString();
		}
	}
	
	public static boolean straight(int[] cards) {
			if(cards[0] + 1 == cards[1] && cards[1] + 1 == cards[2] && cards[2] + 1 == cards[3] && cards[3] + 1 == cards[4]) {
				return true;
			} else {
				return false;
			}
		}
		
	public static boolean flush(char[] suit) {
		//This method is the only one that uses the suit array
		if(suit[0] == suit[1] && suit[0] == suit[2] && suit[0] == suit[3] && suit[0] == suit[4]) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean fourOfAKind(int[] cards) {
		//The numbers being sorted is key for these comparisons
		if((cards[0] == cards[1] && cards[1] == cards[2] && cards[2] == cards[3]) || (cards[1] == cards[2] && cards[2] == cards[3] && cards[3] == cards[4]))  {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean fullHouse(int[] cards) {
		int firstKind = 0;
		int secondKind = 0;
		//because they are sorted the two outcomes of a full house can only be first 3 followed by 2 or first 2 followed by 3.
		for(int i = 0; i < 2; i++) {
		if(cards[i] == cards[2]) {
			firstKind++;
			}
		}
		if(cards[0] == cards[1] && firstKind == 0) {
			firstKind = firstKind + 2;
		} else if(cards[0] == cards[1] && firstKind != 0) {
			firstKind++;
		}
		
		for(int j = 3; j < 5; j++) {
			if(cards[2] == cards[j]) {
				secondKind++;
			}
		}
		if(cards[3] == cards[4] && secondKind == 0){
			secondKind = secondKind + 2;
		} else if(cards[3] == cards[4] && secondKind != 0) {
			secondKind++;
		}
		
		if((firstKind == 2 && secondKind == 3) || (firstKind == 3 && secondKind == 2)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean threeOfAKind(int[] cards) {
		if((cards[0] == cards[1] && cards[1] == cards[2] && cards[0] == cards[2]) || (cards[1] == cards[2] && cards[2] == cards[3] && cards[1] == cards[3]) || (cards[2] == cards[3] && cards[3] == cards[4] && cards[2] == cards[4])) {
			return true;
		} else{
			return false;
		}
	}
	
	public static boolean twoPair(int[] cards) {
		if(cards[0] == cards[1] && cards[2] == cards[3]) { //First Scenario
			return true;
		} else if(cards[1] == cards[2] && cards[3] == cards[4]) { //Second Scenario
			return true;
		} else if(cards[0] == cards[1] && cards[3] == cards[4]) { //Third and Final Scenario
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean pair(int[] cards) {
		//checks there is only 1 pair so there is no four of a kind, 3 of a kind, or 2 pair confusion
		int pairCount = 0;
		int j = 1;
		for(int i = 0; i < cards.length - 1; i++) {
			if(cards[i] == cards[j]) {
				pairCount++;
			}
			j++;
		}
		if(pairCount == 1) {
			return true;
		} else { 
			return false;
		}
	}
	
	public static boolean hiCard(int[] cards) {
		//Most common. Only returns true
		return true;
	}
	
//Final Bracket	
}