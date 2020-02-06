package inf101.v20.lab3.cellular.datastructure;

import inf101.v20.lab3.cellular.CellState;

import java.util.ArrayList;

/**
 * 
 * A Grid contains a set of {@link inf101.v20.lab3.cellular.CellState CellStates}
 * 
 * @author <Torbjørn Lunde Jensen> - <tje012@uib.no>
 */
public class CellGrid implements IGrid {

    private final int width;
    private final int height;
    ArrayList<CellState> cellStateList = new ArrayList<>();

    public CellGrid(int width, int height, CellState state) {
        this.width = width;
        this.height = height;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                cellStateList.add(state);
            }
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void set(int x, int y, CellState element) {
        if (x <= width && x >= 0 && y <= height && y >= 0) {
            cellStateList.set(x * height + y, element);
        }
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public CellState get(int x, int y) {
        if(x <= width && x >= 0 && y <= height && y >= 0) {
            return cellStateList.get(x * height + y);
        }
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public IGrid copy() {
        CellGrid newGrid = new CellGrid(this.getWidth(), this.getHeight(), CellState.DEAD);
        for(int x = 0; x < this.getWidth(); x++) {
            for(int y = 0; y < this.getHeight(); y++) {
                newGrid.set(x, y, this.get(x, y));
            }
        }
        return newGrid;
    }
}
