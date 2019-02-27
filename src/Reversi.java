import java.util.HashSet;
import java.util.Iterator;

import controller.ReversiController;
import model.Point;
import model.ReversiModel;
import model.UnivalidPointException;
import model.UnvalidInputException;
import view.ReversiView;

/**
 * This class construct the process of the Reversi game, it will call other classes to get input and output, 
 * initialize the table, place tokens, and check which player wins.
 * 
 * @author Jerry Zhu
 *
 */
public class Reversi {

	private static boolean end = false;
	private static boolean valid;

	/**
	 * This is the main method which not only read the input, check the placeable positions,
	 * but also keep track of the whole game processing.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		ReversiView view = new ReversiView();
		String input = view.getInput();
		input = input.toLowerCase();

		while(input.equals("yes")) {
			ReversiModel model = new ReversiModel();
			ReversiController controller = new ReversiController(model);
			HashSet<Point> playerPlaceablePositions = new HashSet<>();
			HashSet<Point> computerPlaceablePositions = new HashSet<>();
			Point temp = null;
			while(!end) {
				valid = false;
				// We need to get the placeable locations, and print the statistics first.
				int statistics1 = getSides("W", model);
				int statistics2 = getSides("B", model);
				controller.getPlaceableLocations("W", "B", playerPlaceablePositions, model);
				if(!playerPlaceablePositions.isEmpty()) {
					showPlaceableLocations(playerPlaceablePositions, model);
					view.printTable(model);
					view.printStatistics(statistics1, statistics2);
					model.cleanStars();
					view.askInput();
				}
				// The player's turn.
				while(!valid) {
					if(playerPlaceablePositions.isEmpty()) {
						break;
					}
					input = view.getInput();
					input = input.toLowerCase();
					temp = new Point(getRow(input), getCol(input));
					try {
						if(input.charAt(1) < '1' || input.charAt(1) > '8') {
							throw new UnvalidInputException("");
						}
						if(input.charAt(0) < 'a' || input.charAt(1) > 'h' ) {
							throw new UnvalidInputException("");
						}
						if(!playerPlaceablePositions.contains(temp)) {
							throw new UnivalidPointException("");
						}else {
							valid = true;
							continue;
						}
					}catch(Exception e) {
						view.printException();
					}
				}
				// If the point is valid, place it and flip the tokens.
				if(valid) {
					userTurn(temp, model, controller);
					view.printTable(model);
					statistics1 = getSides("W", model);
					statistics2 = getSides("B", model);
					view.printStatistics(statistics1, statistics2);
				}

				// The computer's step, we do not need to get the input from stdin, but We 
				// should get all of the placeable locations, then determine which move is
				// the best.

				controller.getPlaceableLocations("B", "W", computerPlaceablePositions, model);
				if(!computerPlaceablePositions.isEmpty()) {
					temp = computerPlace(computerPlaceablePositions, model, controller);
					view.ComputerStatistic(temp);
					computerTurn(temp, model, controller);
				}
				// Check if the player or the computer wins.
				if(playerPlaceablePositions.isEmpty() && computerPlaceablePositions.isEmpty()) {
					view.printResult(getSides("W", model), getSides("B", model), model);	
					end = true;
				}else if(model.isFull()) {
					view.printResult(getSides("W", model), getSides("B", model), model);	
					end = true;
				}
				// Otherwise, clean the placeable positions and the stars on the table.
				playerPlaceablePositions.clear();
				computerPlaceablePositions.clear();
			}
			
			// Reset the game.
			view = new ReversiView();
			input = view.getInput();
			input = input.toLowerCase();
			end = false;
		}
	}



	/**
	 * This method is to get the row from the input.
	 * 
	 * @param input A two characters long string, the second character represent the row.
	 * @return the row that the player enter.
	 */
	private static int getRow(String input) {
		int row = input.charAt(1)-49;
		return row;

	}

	/**
	 * This method is to get the column from the input.
	 * 
	 * @param input A two characters long string, the first character represent the column.
	 * @return the column that the player enter.
	 */
	private static int getCol(String input) {
		input = input.toLowerCase();
		int col = input.charAt(0) - 97;
		return col;
	}


	/**
	 * This method is to get the score of the side.
	 * 
	 * @param side The side that we need to get it's score.
	 * @return The score that the side get.
	 */
	private static int getSides(String side, ReversiModel model) {
		int count = 0;
		int size = model.getTableSize();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(model.getTokenAt(i, j).equals(side)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * This method is to place some stars on the board which shows the placeable 
	 * positions.
	 * @param placeablePositions All of the placeable positions we get.
	 */
	private static void showPlaceableLocations(HashSet<Point> placeablePositions, ReversiModel model) {
		Iterator<Point> points = placeablePositions.iterator();
		Point temp = null;
		while(points.hasNext()) {
			temp = points.next();
			model.placeStar(temp.getRow(), temp.getCol());
		}
	}


	/**
	 * This method represent the user turn of the game.
	 * @param point The point the user wanted to place on the table
	 */
	private static void userTurn(Point point, ReversiModel model, ReversiController controller) {
		// Place the point first.
		int row = point.getRow();
		int col = point.getCol();
		model.placeW(row, col);

		// Then we do a flip.
		controller.flipTheOpponent(point, "W", "B", model.getTable()); 
	}

	/**
	 * This method will place a "B" at the board for the computer player.
	 * 
	 * @param placeablePositions The placeable positions for computer player.
	 * @param model The board of the game.
	 * @param controller The controller of the game.
	 * @return The point will be placed on the board.
	 */
	private static Point computerPlace(HashSet<Point> placeablePositions, ReversiModel model, ReversiController controller) {
		String[][] table = model.getTable();
		Iterator<Point> points = placeablePositions.iterator();
		int max = 0;
		int temp = 0;
		int row = 0;
		int col = 0;
		Point move = null;
		Point current = null;
		// Find the point that maximum the score.
		while(points.hasNext()) {
			current = points.next();
			row = current.getRow();
			col = current.getCol();
			table[row][col] = "B";
			controller.flipTheOpponent(current, "B", "W", table);
			temp = getSides("B", model);
			if(max < temp) {
				max = temp;
				move = current;
			}
			table = getTable(model);
		}
		return move;
	}


	/**
	 * This method represent the computer's turn.
	 * 
	 * @param placeablePositions All of the placeable positions that the computer player can place.
	 */
	private static void computerTurn(Point move, ReversiModel model, ReversiController controller) {
		// After we exit the loop, we place the point and flip other tokens.
		model.placeB(move.getRow(), move.getCol());
		controller.flipTheOpponent(move, "B", "W", model.getTable());
	}


	/**
	 * This method will return the current table of the game.
	 *
	 * @param model The current table
	 * @return the current table.
	 */
	public static String[][] getTable(ReversiModel model){
		String[][] table = new String[8][8];
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				table[i][j] = model.getTokenAt(i,j);
			}
		}
		return table;
	}

}
