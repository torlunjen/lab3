package inf101.v20.lab3.cellular;

import java.awt.Color;
import java.util.Random;

/**
 * 
 * The State of a cell, either Alive or Dead
 */
public enum CellState {
	ALIVE,
	DEAD;

	public static CellState random(Random rand){
		return CellState.values()[rand.nextInt(2)];
	}

	/**
	 * Returns the cell state represented as a color
	 * 
	 *  ALIVE => BLACK 
	 *  DEAD => WHITE
	 *  
	 * @return black if the cell is alive, white otherwise
	 */
	public Color asColor() {
		if(this == ALIVE) {
			return Color.GREEN;
		}
		else {
			return null;
		}
	}
}

