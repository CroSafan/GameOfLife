package org.antun.gameOfLife;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private static final long serialVersionUID = 7209434012374778228L;
	public static Cell[][] cells;
	public static final int GRID_SIZE = 80;
	


	public Grid() {
		cells = new Cell[GRID_SIZE][GRID_SIZE];

		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				int random = 0 + Double.valueOf(Math.random()*(0-50)).intValue();
				if(random%2==0)
				cells[i][j] = new Cell(i, j, false);
				else
				cells[i][j] = new Cell(i, j, true);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {

				if (cells[x][y].isAlive()) {
					int cellX = 10 + (cells[x][y].getX() * 10);
					int cellY = 10 + (cells[x][y].getY() * 10);
					g.setColor(Color.GRAY);
					g.fillRect(cellX, cellY, 10, 10);
				}
			}
		}

		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(10, 10, 800, 800);

		for (int i = 10; i <= 810; i += 10) {
			g.drawLine(i, 10, i, 810);
		}

		for (int i = 10; i <= 810; i += 10) {
			g.drawLine(10, i, 810, i);
		}
	}

	public void fillCell(int x, int y) {
		if (x < GRID_SIZE && y < GRID_SIZE)
			cells[x][y].setAlive(true);


		repaint();
	}

	public void removeCell(int x, int y) {
		if (x < GRID_SIZE && y < GRID_SIZE)
			cells[x][y].setAlive(false);
		repaint();
	}
	public void clearGrid(){
		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {
				cells[x][y].setAlive(false);				
			}
		}
		repaint();
	}
	
	
	public void random(){
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				int random = 0 + Double.valueOf(Math.random()*(0-50)).intValue();
				if(random%2==0)
				cells[i][j].setAlive(false);
				else
				cells[i][j].setAlive(true); 
			}
		}
		repaint();
	}

	public int getNumberOfNeighbours(Cell cell) {
		int numbersOfNeighbours = 0;

		if (cells[cell.getX() > 0 ? cell.getX() - 1 : GRID_SIZE - 1][cell.getY() > 0 ? cell.getY() - 1 : GRID_SIZE - 1]
				.isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX()][cell.getY() > 0 ? cell.getY() - 1 : GRID_SIZE - 1].isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX() < GRID_SIZE - 1 ? cell.getX() + 1 : 0][cell.getY() < GRID_SIZE - 1 ? cell.getY() + 1 : 0]
				.isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX() > 0 ? cell.getX() - 1 : GRID_SIZE - 1][cell.getY()].isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX() < GRID_SIZE - 1 ? cell.getX() + 1 : 0][cell.getY()].isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX() > 0 ? cell.getX() - 1 : GRID_SIZE - 1][cell.getY() < GRID_SIZE - 1 ? cell.getY() + 1 : 0]
				.isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX()][cell.getY() < GRID_SIZE - 1 ? cell.getY() + 1 : 0].isAlive() == true)
			numbersOfNeighbours++;
		if (cells[cell.getX() < GRID_SIZE - 1 ? cell.getX() + 1 : 0][cell.getY() > 0 ? cell.getY() - 1 : GRID_SIZE - 1]
				.isAlive() == true)
			numbersOfNeighbours++;

		return numbersOfNeighbours;
	}

	public void checkConwayRules(int x, int y) {
		// X Any live cell with fewer than two live neighbours dies, as if
		// caused by underpopulation.
		// X Any live cell with more than three live neighbours dies, as if by
		// overpopulation.
		// X Any live cell with two or three live neighbours lives on to the
		// next generation.
		// Any dead cell with exactly three live neighbours becomes a live cell,
		// as if by reproduction.
		if (cells[x][y].isAlive()) {
			if (getNumberOfNeighbours(cells[x][y]) < 2 || getNumberOfNeighbours(cells[x][y]) > 3)
				cells[x][y].setNextGeneration(false);
			else if (getNumberOfNeighbours(cells[x][y]) == 3 || getNumberOfNeighbours(cells[x][y]) == 2)
				cells[x][y].setNextGeneration(true);
		}

		if (!cells[x][y].isAlive() && getNumberOfNeighbours(cells[x][y]) == 3)
			cells[x][y].setNextGeneration(true);

	}

	public  void checkAndApplyRules() {
		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {
				checkConwayRules(x, y);
			}
		}

		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {

				cells[x][y].setStateForNextGen();
			}

		}
	}
}
