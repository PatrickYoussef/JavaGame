// -----------------------------------------------
// Assignment #4
// Written by : Patrick Youssef #40098029
// For COMP 248 section #2182 - Fall 2018
// -----------------------------------------------

package assignment4;

public class Dice {

	private int dice1;
	private int dice2; // initializing our 2 instance variables

	public Dice() { // setting the values of the variables at 0 when calling this class in the
					// driver
		this.dice1 = 0;
		this.dice2 = 0;
	}

	public int getDice1() { // Getting the value of dice1
		return dice1;
	}

	public int getDice2() { // Getteing the value of dice2
		return dice2;
	}

	public int rollDice() { // Returning the sum of 2 random values of the die, each one between 1 and 6

		do {
			dice1 = (int) (Math.random() * 7);
		} while (dice1 == 0);

		do {
			dice2 = (int) (Math.random() * 7);
		} while (dice2 == 0);

		return dice1 + dice2;
	}

	public String toString() { // returning a sentence that says the value of the die
		String dice = "( dice 1 = " + dice1 + " , dice 2 = " + dice2 + " )";
		return dice;
	}

}
