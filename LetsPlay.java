// -----------------------------------------------
// Assignment #4
// Written by : Patrick Youssef #40098029
// For COMP 248 section #2182 - Fall 2018
// -----------------------------------------------
package assignment4;

import java.util.Scanner;

public class LetsPlay {

	public static void main(String[] args) {
		System.out.println("*******************************************");
		System.out.println("******WELCOME TO PATRICK'S GARDEN !!!******"); // Welcome banner
		System.out.println("*******************************************");
		System.out.println();

		Scanner key = new Scanner(System.in);

		// Rules of the game
		System.out.println("This game is all about strategy and a bit of luck. Choose the size of your garden NxN");
		System.out.println("The goal is to fill your garden with flowers and trees");
		System.out.println("A flower take one spot and a tree takes 4 spots (2x2)");
		System.out.println("Roll the dice and the number that you have will decide your next move.");
		System.out.println();
		System.out.println("Rolls and their outcome:");
		System.out.println("3: plant a tree and a flower");
		System.out.println("6: plant 2 flowers (2 spots)");
		System.out.println("12: plant 2 trees (2 times 2x2)");
		System.out.println();
		System.out.println("5 or 10: The rabbit eats something you have planted,");
		System.out.println("it will either be a flower of a part of a tree (1 spot)");
		System.out.println("Any other EVEN rolls: plant a tree 2x2");
		System.out.println("Any other ODD rolls: plant a flower 1x1");
		System.out.println();
		System.out.println("Minimum number of players: 2");
		System.out.println("Minimum garden size: 3x3");
		System.out.println("You can only plant in empty locations");
		System.out.println(
				"To plant a tree, enter the top left coordinates of the 2x2 space, and I'll check if you have all 4 free locations");
		System.out.println("Now, let's START the game and may the force be with you!!");
		System.out.println();
		System.out.println();
		System.out.println("The default garden size is a 3x3 square. You can use this default board size");
		System.out.println("or change the size");
		System.out.println();
		System.out.print("Enter 0 to use the default garden size or -1 to enter your own size: ");
		int gardenChoice = key.nextInt();

		boolean value = false;
		int gardensize = 0;

		// Prompting the user to choose the default gardensize (3x3) or to choose his
		// own size
		do {
			if (gardenChoice == 0) {
				gardensize = 3;
				value = true;

			} else if (gardenChoice == -1) {
				System.out.print("What size board would you like? (Minimum 3): ");
				gardensize = key.nextInt();
				while (gardensize < 3) {
					System.out.print("You cannot have a garden smaller than 3x3, enter your size: ");
					gardensize = key.nextInt();
				}
				value = true;

			} else if ((gardenChoice != 0) || (gardenChoice != -1)) {
				System.out.print("Sorry, but " + gardenChoice + " is not a legal choice. Enter your choice: ");
				gardenChoice = key.nextInt();
			}
		} while (value == false);
		System.out.println();

		// Prompting the user to tell him how many players there will be
		int playerNum;
		do {
			System.out.print("How many gardeners will there be ? (Minimum required 2 to play, maximim 10) ");
			playerNum = key.nextInt();
			if ((playerNum < 2) || (playerNum > 10))
				System.out.println(" ** Sorry, but " + playerNum + " is not a legal number of player");
		} while (playerNum < 2 || playerNum > 10);
		System.out.println();

		// Prompting the user to enter the player's names
		String[] name = new String[playerNum];
		for (int i = 0; i < playerNum; i++) {
			System.out.print("Name of player " + i + " (no spaces allowed): ");
			String Name1 = key.next();
			name[i] = Name1;
		}
		// Creating an array of objects of the class Player, and then calling each
		// object with their name, and the same garden size
		Player[] player = new Player[playerNum];
		for (int i = 0; i < playerNum; i++) {
			player[i] = new Player(name[i], gardensize);
		}

		Dice dice = new Dice(); // Creating an object of the class Dice so we can roll the dice afterwards
		int[] diceValue = new int[playerNum]; // Creating an array of values so we can store the values of each player's
												// roll

		System.out.println();
		System.out.println("Let's see who goes first...");

		boolean Continue = false; // Each player rolls the dice, and then we go through everyone's value and check
									// which one is bigger
		do { // If two of them have the same value, do it again till everyone has different
				// values
			for (int i = 0; i < playerNum; i++) {
				diceValue[i] = dice.rollDice();
				System.out.println(player[i].getName() + " rolled a " + diceValue[i]);
				for (int k = 0; k < playerNum; k++) {
					if ((diceValue[i] == diceValue[k]) && (i != k)) {
						System.out.println("We will start over as " + diceValue[i] + " was rolled by "
								+ player[k].getName() + " as well.");
						Continue = false;
						break;
					} else
						Continue = true;
				}
				if (Continue == false)
					break;
			}

			if (Continue == false) {
				for (int k = 0; k < playerNum; k++)
					diceValue[k] = 0;
			}
			System.out.println();
		} while (Continue == false);

		// Putting the player with the highest roll in a new variable called 'max'
		int highest = diceValue[0];
		int max = 0;

		for (int i = 0; i < playerNum; i++) {
			if (diceValue[i] > highest) {
				highest = diceValue[i];
				max = i;
			}
		}
		System.out.println(player[max].getName() + " goes first.");
		System.out.println();
		System.out.println("Time to play !!!!!");
		System.out.println("-------------------");
		System.out.println();

		int count = 0;

		for (int i = max; i < playerNum; i++) { // We start the loop at 'max', the player who had the highest roll to
												// the last player

			int num = dice.rollDice();
			if (num == 3) { // If the player rolled a 3, he plants a tree and a flower
				System.out.println(player[i].getName() + " you rolled a " + num + " " + dice);
				System.out.println("You must plant a tree (2x2) and a flower (1x1)");
				System.out.println();
				System.out.println(player[i].showGarden());
				System.out.println();

				if (player[i].howManyTreesPossible() != 0) { // Checking if the player can plant a tree
					System.out.println("Let's start with the tree. You have " + player[i].howManyTreesPossible()
							+ " places to do this.");
					System.out.print("Enter coordinates as row, confirm it, then enter column: ");
					int row = key.nextInt();
					int column = key.nextInt(); // Prompting the user for coordinates
					while (player[i].TreeOut(row) == false || player[i].TreeOut(column) == false) { // Checking if the
																									// player didn't out
																									// of bounds
																									// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if there is already something
																			// planted where the player wants to plant
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantTreeInGarden(column, row); // Planting a tree
					System.out.println(player[i].showGarden());
					System.out.println();

				} else if (player[i].howManyTreesPossible() == 0) // If the player does not have place to plant, a tree,
																	// he skips his turn
					System.out.println("You cannot plant a tree !!");
				System.out.println();

				if (player[i].howManyFlowersPossible() != 0) { // Checking if the player can plant a flower
					System.out.println("You still have a flower (1x1) to plant. ");
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println();
					System.out.println("You have " + player[i].howManyFlowersPossible() + " places to do this.");
					System.out.print("Enter coordinates as row column: ");
					int row = key.nextInt(); // Prompting the user for coordinates
					int column = key.nextInt();
					while (player[i].FlowerOut(row) == false || player[i].FlowerOut(column) == false) { // Checking if
																										// the player
																										// didn't out of
																										// bounds
																										// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if the player is planting in an
																			// empty location
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantFlowerInGarden(column, row); // Planting flower
					System.out.println(player[i].showGarden());
					System.out.println();

				} else if (player[i].howManyFlowersPossible() == 0) // The player skips his turn if he does not have
																	// place to plant
					System.out.println("You cannot plant a flower, you pass your turn !");
				System.out.println();

			} else if (num == 6) { // If the player rolls a 6, he plants 2 flowers
				System.out.println(player[i].getName() + " you rolled a " + num + " " + dice);
				System.out.println("You must plant 2 flowers (1x1)");
				System.out.println("You have " + player[i].howManyFlowersPossible() + " places to do this.");
				if (player[i].howManyFlowersPossible() != 0) { // Checking if the user has palces to plant a flower
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.print("First flower - Enter coordinates as row column: ");
					int row = key.nextInt();
					int column = key.nextInt();
					while (player[i].FlowerOut(row) == false || player[i].FlowerOut(column) == false) { // Checking if
																										// the player
																										// didn't out of
																										// bounds
																										// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if the player is planting in an
																			// empty location
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ +player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantFlowerInGarden(column, row);
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println();
					System.out.print("Second flower - Enter coordinates as row column: ");
					row = key.nextInt();
					column = key.nextInt();
					while (player[i].FlowerOut(row) == false || player[i].FlowerOut(column) == false) { // Checking if
																										// the player
																										// didn't out of
																										// bounds
																										// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt(); // Prompting the user for coordinates
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if the player is planting in an
																			// empty location
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantFlowerInGarden(column, row); // planting a flower
					System.out.println(player[i].showGarden());
					System.out.println();

				} else // The user skips its turn if he does not have places to plant a flower
					System.out.println("Sorry, you miss your turn, you cannot plant flowers");
				System.out.println();

			} else if (num == 12) { // If the player rols 12, he can plants 2 trees
				System.out.println(player[i].getName() + " you rolled a " + num + " " + dice);
				System.out.println("You must plant 2 trees (2x2)");
				System.out.println();
				System.out.println(player[i].showGarden());
				if (player[i].howManyTreesPossible() != 0) { // Checking if the user has places to plant trees
					System.out.println("There is enough room for " + player[i].howManyTreesPossible()
							+ " tree(s) in your Garden. Enter coordinates as row column: ");
					int row = key.nextInt();
					int column = key.nextInt();
					while (player[i].TreeOut(row) == false || player[i].TreeOut(column) == false) {// Checking if the
																									// player didn't out
																									// of bounds
																									// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if there is an empty location
																			// where the user wants to plant
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantTreeInGarden(column, row); // planting a tree
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println();

				} else // Skips a turn if there is no place to plant a tree
					System.out.println("Sorry, you miss your turn, you cannot plant 2 trees");

				System.out.println("For the second tree, there is room for " + player[i].howManyTreesPossible()
						+ " tree(s) in your garden");
				if (player[i].howManyTreesPossible() != 0) { // Checking if the user has places to plant trees
					System.out.print("Plant your second tree. Enter coordinates as row column: ");
					int row = key.nextInt();
					int column = key.nextInt();
					while (player[i].TreeOut(row) == false || player[i].TreeOut(column) == false) { // Checking if the
																									// player didn't out
																									// of bounds
																									// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if there is an empty location
																			// where the user wants to plant
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantTreeInGarden(column, row); // planting a tree
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println();

				} else {
					System.out.println("You skip your turn since there is no place to plant a tree.");
					System.out.println();
				}

			} else if ((num == 5) || (num == 10)) { // The rabbit randomly eats a taken location in a garden
				System.out.println(player[i].getName() + " you rolled a " + num + " " + dice);
				System.out.println();
				System.out.println(player[i].showGarden());
				System.out.println();
				if (player[i].isGardenEmpty()) { // Skips turn is garden is empty
					System.out.println("The rabbit can't eat anything, your garden is already empty !!");
					System.out.println();
				} else { // The rabbit eats a taken location
					{
						int rabbitRow = player[i].showRow();
						int rabbitColumn = player[i].showColumn();
						while (player[i].WhatIsPlanted(rabbitColumn, rabbitRow) == '-') { // Looking for a taken
																							// location so the rabbit
																							// eats a flower or a tree
							rabbitRow = player[i].showRow();
							rabbitColumn = player[i].showColumn();
						}
						System.out.println("The rabbit ate whatever was planted in location (" + rabbitRow + ","
								+ rabbitColumn + ")");
						player[i].eatHere(rabbitColumn, rabbitRow); // Eating a location
						System.out.println();
						System.out.println(player[i].showGarden());
					}
				}

			} else if (num == 2 || num == 4 || num == 8) { // If the player rolls 2, 4 or 8, the user plants a tree
				System.out.println(player[i].getName() + " you rolled " + num + " " + dice);
				if (player[i].howManyTreesPossible() != 0) { // Checking if the user has places to plant trees
					System.out.println("You must plant a tree (2x2)");
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println("and have " + player[i].howManyTreesPossible() + " places to do it.");
					System.out.println("Enter coordinates as row, confirm it, and the column");
					int row = key.nextInt();
					int column = key.nextInt();
					while (player[i].TreeOut(row) == false || player[i].TreeOut(column) == false) { // Checking if the
																									// player didn't out
																									// of bounds
																									// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if there is an empty location
																			// where the user wants to plant
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantTreeInGarden(column, row); // plant a tree
					System.out.println(player[i].showGarden());
					System.out.println();

				} else {
					System.out.println("Sorry, you must pass your turn, you cannot plant a tree");
					System.out.println();
				}

			} else if (num == 7 || num == 9 || num == 11) { // If the player rolls 7, 9 or 11, the user plants a tree
				System.out.println(player[i].getName() + " you rolled " + num + " " + dice);
				if (player[i].howManyFlowersPossible() != 0) { // Checking if the user has places to plant trees
					System.out.println("You must plant a flower (1x1)");
					System.out.println();
					System.out.println(player[i].showGarden());
					System.out.println("and have " + player[i].howManyFlowersPossible() + " places to do it.");
					System.out.println("Enter coordinates as row, confirm it, and the column");
					int row = key.nextInt();
					int column = key.nextInt();
					while (player[i].FlowerOut(row) == false || player[i].FlowerOut(column) == false) { // Checking if
																										// the player
																										// didn't out of
																										// bounds
																										// coordinate
						System.out.println("You are entering a coordinate out of the garden size");
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					while (player[i].WhatIsPlanted(column, row) != '-') { // Checking if the player didn't out of bounds
																			// coordinate
						System.out.println("You can't plant anything there ! It is already taken by a "
								+ player[i].WhatIsPlanted(column, row));
						System.out.print("Enter new coordinates as row and then column: ");
						row = key.nextInt();
						column = key.nextInt();
					}
					player[i].plantFlowerInGarden(column, row);// plants a flower
					System.out.println(player[i].showGarden());
					System.out.println();

				} else
					System.out.println("Sorry, you must pass your turn, you cannot plant a tree");
			}

			int m = (int) ((count++) / playerNum) + 1; // counting the number of rounds

			if (player[i].isGardenFull()) { // Checking if one player has his garden full
				System.out.println("FINAL RESULTS");
				System.out.println("-----------------------");
				System.out.println("Here are the results after " + m + " rounds.");
				for (int q = 0; q < playerNum; q++)// Showing everybody's round
					System.out.println(player[q].showGarden());

				System.out.println();
				System.out.println("And the winner is...... " + player[i].getName()); // Saying who the winner is
				System.out.println("What a beautiful garden you have!!!");
				System.out.println();
				System.out.println("Hope you had fun!!!!");
				System.exit(0); // Exit the program because someone won
			}
			if (i == playerNum - 1)
				i = -1;
		}

		key.close();
	}
}