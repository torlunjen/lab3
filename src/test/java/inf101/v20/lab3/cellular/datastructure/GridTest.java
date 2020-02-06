package inf101.v20.lab3.cellular.datastructure;


import inf101.v20.lab3.cellular.CellState;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GridTest {
	Random random = new Random();

	@Test
	public void constructTest1() {
		int height = random.nextInt(100)+1, width = random.nextInt(100)+1;
		IGrid grid = new CellGrid(width, height, CellState.DEAD);
		assertEquals(grid.getHeight(), height);
		assertEquals(grid.getWidth(), width);
	}
	
	/**
	 * Tests that trying to access outside of the dimensions of the grid throws
	 * an IndexOutOfBoundsException.
	 */
	@Test
	public void outOfBoundsSetTest() {
		IGrid grid = new CellGrid(10, 10, CellState.DEAD);

		try {
			grid.set(11, 0, CellState.DEAD);
			fail("Should throw exception");
		} catch (IndexOutOfBoundsException e) {
			;
		}
		try {
			grid.set(0, 11, CellState.DEAD);
			fail("Should throw exception");
		} catch (IndexOutOfBoundsException e) {
			;
		}
	}
	
	/**
	 * Tests that trying to access outside of the dimensions of the grid throws
	 * an IndexOutOfBoundsException.
	 */
	@Test
	public void outOfBoundsGetTest() {
		IGrid grid = new CellGrid(10, 10, CellState.DEAD);

		try {
			grid.get(11, 0);
			fail("Should throw exception");
		} catch (IndexOutOfBoundsException e) {
			;
		}
		try {
			grid.get(0, 11);
			fail("Should throw exception");
		} catch (IndexOutOfBoundsException e) {
			;
		}
	}

	@Test
	public void setGetTest() {
		IGrid grid = new CellGrid(100, 100, CellState.DEAD);

		for (int x = 0; x < 100; x++)
			for (int y = 0; y < 100; y++) {
				CellState cs = CellState.random(random);
				grid.set(x, y, cs);
				assertEquals(cs, grid.get(x, y));
			}
	}
	
	@Test
	public void getSetIndependentCellsTest() {
		IGrid grid = new CellGrid(100, 100, random.nextBoolean() ? CellState.DEAD : CellState.ALIVE);

		for (int x = 0; x < 100; x++)
			for (int y = 0; y < 100; y++) {
				CellState current = grid.get(x, y);
				CellState cs = CellState.random(random);
				int i = x<100-1 ? x+1 : x-1;
				int j = y<100-1 ? y+1 : y-1;
				grid.set(i, j, cs);
				assertEquals(current, grid.get(x, y));
			}
	}

	@Test
	public void setGetIndependentTest() {
		IGrid grid = new CellGrid(100, 100, CellState.DEAD);

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				grid.set(x, y, CellState.random(random));
			}
		}

		for (int x = 0; x < 100; x++)
			for (int y = 0; y < 100; y++) {
				CellState cs = CellState.random(random);
				grid.set(x, y, cs);
				assertEquals(cs, grid.get(x, y));
			}
	}

	@Test
	public void copyTest() {
		IGrid grid = new CellGrid(100, 100, CellState.DEAD);

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				CellState cs = CellState.random(random);
				grid.set(x, y, cs);
			}
		}

		IGrid newGrid = grid.copy();
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				assertEquals(grid.get(x, y), newGrid.get(x, y));
			}
		}
	}
}
