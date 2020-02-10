package inf101.v20.lab3.cellular.gui;

import inf101.v20.lab3.cellular.BriansBrain;
import inf101.v20.lab3.cellular.CellAutomaton;
import inf101.v20.lab3.cellular.GameOfLife;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		UIManager.getSystemLookAndFeelClassName();

		JFrame frame = new JFrame("INF101 Cell Automaton & Brians Brain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 2));
		frame.setBackground(Color.BLACK);
		//frame.setResizable(true);
		//frame.setLocationByPlatform(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


		CellAutomaton gol = new GameOfLife(200,200);
		CellAutomaton bb = new BriansBrain(200,200);
		CellAutomataGUI gameOfLife = CellAutomataGUI.run(gol);
		CellAutomataGUI briansBrain = CellAutomataGUI.run(bb);



		frame.add(gameOfLife);
		frame.add(briansBrain);
		frame.pack();
		frame.setVisible(true);
	}

}
