// -----------------------------------------------
// Assignment #4
// Written by : Patrick Youssef #40098029
// For COMP 248 section #2182 - Fall 2018
// -----------------------------------------------

package assignment4;

public class Garden {
	private char[][] garden = new char[3][3]; // Initializing a 2d array 3x3

	public Garden() { // constructor that initializes a 3x3 garden
		initializeGarden();
	}

	public Garden(int x) { // constructor that initializes the garden with the user size's choice
		garden = new char[x][x];
		initializeGarden();
	}

	private void initializeGarden() { // Creating the garden

		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden[i].length; j++) {
				garden[i][j] = '-';
			}
		}
	}

	public char getInLocation(int r, int c) { // Knowing what is the location
		return garden[r][c];
	}

	public void plantFlower(int r, int c) { // planting a flower at the lcoation wanted
		garden[r][c] = 'f';
	}

	public void plantTree(int r, int c) { // planting a tree at the lcoation wanted
		garden[r][c] = 't';
		garden[r + 1][c] = 't';
		garden[r][c + 1] = 't';
		garden[r + 1][c + 1] = 't';
	}

	public void removeFlower(int r, int c) { // removing a flower
		garden[r][c] = '-';
	}

	public int row() { // returning a random coordinate between 0 and the garden.length
		int r = (int) (Math.random() * garden.length);
		return r;
	}

	public int column() { // returning a random coordinate between 0 and the garden.length
		int c = (int) (Math.random() * garden.length);
		return c;
	}

	public boolean flowerOut(int r) { // return true if the coordinate is in the garden's bounds
		if (r < garden.length) {
			return true;
		} else {
			return false;
		}
	}

	public boolean treeOut(int r) { // return true if the coordinate is in the garden's bounds
		if (r < garden.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	public int countPossibleTrees() { // Counting the possible trees that the player can plant
		int count = 0;
		for (int i = 0; i < garden.length - 1; i++) {
			for (int j = 0; j < garden[i].length - 1; j++) {
				if ((garden[i][j] == '-') && (garden[i + 1][j] == '-') && (garden[i][j + 1] == '-')
						&& (garden[i + 1][j + 1] == '-')) {
					count++;
				}
			}
		}
		return count;
	}

	public int length() { // returning the garden's length
		return garden.length;
	}

	public int countPossibleFlowers() { // Counting the number of flowers the user can plants
		int Count = 0;
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden[i].length; j++) {
				if (garden[i][j] == '-')
					Count++;

			}
		}
		return Count;

	}

	public boolean GardenFull() { // Checking of the garden is full
		int full = garden.length * garden.length;
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden[i].length; j++) {
				if (garden[i][j] != '-')
					full--;

			}

		}
		if (full == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean GardenEmpty() { // Checking if the garden is empty
		int full = garden.length * garden.length;
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden[i].length; j++) {
				if (garden[i][j] == '-')
					full--;

			}

		}
		if (full == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() { // Printing the garden with the rows number and columns number

		String gardenobj = "    |";
		for (int i = 0; i < garden.length; i++)
			gardenobj += " " + i + "  ";
		gardenobj += "\n\n";
		for (int j = 0; j < garden.length; j++) {
			gardenobj += j + "   | ";
			for (int k = 0; k < garden.length; k++)
				gardenobj += garden[k][j] + "   ";
			gardenobj += "\n\n";
		}

		return gardenobj;
	}
}
