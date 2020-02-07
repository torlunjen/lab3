package inf101.v20.lab3.cellular.gui;

import inf101.v20.lab3.cellular.BriansBrain;
import inf101.v20.lab3.cellular.CellAutomaton;
import inf101.v20.lab3.cellular.GameOfLife;

public class Main {

	public static void main(String[] args) {

		//CellAutomaton ca = new GameOfLife(100,100);
		CellAutomaton ca = new BriansBrain(100,100);
		CellAutomataGUI.run(ca);
	}

}
