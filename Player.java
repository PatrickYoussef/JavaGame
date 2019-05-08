// -----------------------------------------------
// Assignment #4
// Written by : Patrick Youssef #40098029
// For COMP 248 section #2182 - Fall 2018
// -----------------------------------------------

package assignment4;

public class Player {

	private String name; // Initializing instance variables
	private Garden garden = new Garden(); // Creating an object of the class garden

	// This class is about creating an object for each player in the driver's class,
	// it returns the methods of the garden's class

	public Player(String name, int size) {
		this.name = name;
		garden = new Garden(size);
	}

	public String getName() {
		return name;
	}

	public int howManyFlowersPossible() {
		return garden.countPossibleFlowers();
	}

	public int howManyTreesPossible() {
		return garden.countPossibleTrees();
	}

	public char WhatIsPlanted(int r, int c) {
		return garden.getInLocation(r, c);
	}

	public void plantFlowerInGarden(int r, int c) {
		garden.plantFlower(r, c);
	}

	public void plantTreeInGarden(int r, int c) {
		garden.plantTree(r, c);
	}

	public void eatHere(int r, int c) {
		garden.removeFlower(r, c);
	}

	public int showRow() {
		return garden.row();
	}

	public int showColumn() {
		return garden.column();
	}

	public int showLength() {
		return garden.length();
	}

	public boolean FlowerOut(int r) {
		return garden.flowerOut(r);
	}

	public boolean TreeOut(int r) {
		return garden.treeOut(r);
	}

	public boolean isGardenFull() {
		return garden.GardenFull();
	}

	public boolean isGardenEmpty() {
		return garden.GardenEmpty();
	}

	public String showGarden() {
		return garden.toString();
	}

}
