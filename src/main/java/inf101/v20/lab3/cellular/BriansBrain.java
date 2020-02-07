package inf101.v20.lab3.cellular;

import inf101.v20.lab3.cellular.datastructure.CellGrid;
import inf101.v20.lab3.cellular.datastructure.IGrid;

import java.util.Random;

public class BriansBrain implements CellAutomaton {
    private IGrid currentGeneration;

    /**
     *
     * Construct a Game Of Life Cell Automaton that holds cells in a grid of the provided size
     *
     * @param height The height of the grid of cells
     * @param width The width of the grid of cells
     */
    public BriansBrain(int width, int height) {
        currentGeneration = new CellGrid(width, height, CellState.DEAD);
    }

    @Override
    public void initializeCells() {
        Random random = new Random();
        for (int x = 0; x < currentGeneration.getWidth(); x++) {
            for (int y = 0; y < currentGeneration.getHeight(); y++) {
                switch (random.nextInt(3)) {
                    case 0:
                        currentGeneration.set(x, y, CellState.ALIVE);
                        break;
                    case 1:
                        currentGeneration.set(x, y, CellState.DEAD);
                        break;
                    case 2:
                        currentGeneration.set(x, y, CellState.DYING);
                }
            }
        }
    }

    @Override
    public int numberOfRows() {
        return currentGeneration.getHeight();
    }

    @Override
    public int numberOfColumns() {
        return currentGeneration.getWidth();
    }

    @Override
    public CellState getCellState(int x, int y) {
        return currentGeneration.get(x, y);
    }

    @Override
    public void step() {

        IGrid nextGeneration = new CellGrid(
                currentGeneration.getWidth(), currentGeneration.getHeight(),
                CellState.DEAD);

        for (int x = 0; x < nextGeneration.getWidth(); x++) {
            for (int y = 0; y < nextGeneration.getHeight(); y++) {
                if(currentGeneration.get(x, y) == CellState.DEAD && getLivingNeigbours(x, y) == 2) {
                    nextGeneration.set(x, y, CellState.ALIVE);
                }
                else if(currentGeneration.get(x, y) == CellState.ALIVE) {
                    nextGeneration.set(x, y, CellState.DYING);
                }
            }
        }
        currentGeneration = nextGeneration;
    }

    /**
     * Calculates the number of living neighbors of a cell on position (x, y) on the board
     *
     * Note that a cell has 8 neighbors in total, of which any number between 0 and 8 can be alive.
     * The exception are cells along the boarders of the board: these cells have anywhere between
     * 3 neighbors (in the case of a corner-cell) and 5 neighbors in total.
     *
     * @param x the x-position of the cell
     * @param y the y-position of the cell
     * @return the number of living neighbors
     */
    private int getLivingNeigbours(int x, int y) {
        int numNeighbours = 0;
        for(int neighbourX = x - 1; neighbourX <= x + 1; neighbourX++) {
            for (int neighbourY = y - 1; neighbourY <= y + 1; neighbourY++) {
                try {
                    if (currentGeneration.get(neighbourX, neighbourY) == CellState.ALIVE) {
                        numNeighbours++;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        if(currentGeneration.get(x, y) == CellState.ALIVE) {
            return numNeighbours - 1;
        }
        else return numNeighbours;
    }

}
