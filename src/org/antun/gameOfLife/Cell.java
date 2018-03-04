package org.antun.gameOfLife;

public class Cell {
	private boolean isAlive;
	private int x;
	private int y;
	private boolean nextGeneration;

	public Cell(int x, int y, boolean isAlive) {
		this.isAlive = isAlive;
		this.x = x;
		this.y = y;
	}

	public void setStateForNextGen() {
		isAlive = nextGeneration;
	}

	public boolean isNextGeneration() {
		return nextGeneration;
	}

	public void setNextGeneration(boolean nextGeneration) {
		this.nextGeneration = nextGeneration;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
