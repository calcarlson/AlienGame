package edu.project.fin;

public class Driver {

    public static void main(String[] args) {
	Player player = new Player("Bob", 6.0, true, 250);
	GUI gui = new GUI(player);
	gui.startMenu();
    }
}
