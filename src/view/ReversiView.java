package view;
import java.util.Scanner;

import model.Point;
import model.ReversiModel;

/**
 * This class is the view of the Reversi game which is not only get 
 * the input from the user, but also to print the outputs.
 * 
 * @author Jerry Zhu
 *
 */
public class ReversiView {

	/**
	 * This is the constructor of the view which initialize a menu and
	 * print it out.
	 */
	public ReversiView() {
		String menu = "Welcome to Reversi!\n\n";
		menu += "You are W.\n\n";
		menu += "The stars are the placeable locations.\n\n";
		menu += "Enter \"yes\" to start the game: ";
		System.out.print(menu);
	}

	/**
	 * This method is to get input from the users.
	 * 
	 * @return Each command entered by the users.
	 */
	@SuppressWarnings("resource")
	public String getInput() {
		Scanner input = new Scanner(System.in);
		String command = input.next();
		command = command.toLowerCase();
		return command;
	}

	/**
	 * This method is to print the current table.
	 * 
	 * @param model The current model which contains a table.
	 */
	public void printTable(ReversiModel model) {
		int size = model.getTableSize();
		System.out.println();
		for(int i = 0; i < size; i++) {
			System.out.print(i+1);
			for(int j = 0; j < size; j++) {
				System.out.print(" " + model.getTokenAt(i, j));
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		System.out.println();
	}
	
	/**
	 * This method will ask the user a position.
	 */
	public void askInput() {
		System.out.print("Where would you like to place your token?");
	}
	
	/**
	 * This method will print the point that the computer player put.
	 * @param p
	 */
	public void ComputerStatistic(Point p) {
		System.out.println("The computer places a piece at: " + p);
	}

	/**
	 * This method is to ask the user for another input.
	 * 
	 * @param score1 The user's score.
	 * @param score2 The computer's score.
	 */
	public void printStatistics(int score1, int score2) {
		System.out.println("The score is " + score1 + "-" + score2 + ".");
	}

	/**
	 * This method is to print a message when catches a exception.
	 */
	public void printException() {
		System.out.print("Please enter a valid input: ");
	}

	/**
	 * This method is to print the final result of the game.
	 * 
	 * @param player The score that the player get on the game.
	 * @param computer The score that the computer get on the game.
	 */
	public void printResult(int player, int computer, ReversiModel model) {
		printTable(model);
		if(player > computer) {
			System.out.println("You win! The final score is: " + player + " - " + computer);
		}else if(player == computer){
			System.out.println("You are so close! The final score is: " + player + " - " + computer);
		} else {
			System.out.println("You lose. The final score is: " + player + " - " + computer);
		}
		
	}

}
