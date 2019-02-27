package controller;

import java.util.HashSet;
import model.Point;
import model.ReversiModel;

/**
 * This class is the controller of the Reversi game which has the following purpose:
 * 1. check the placeable locations on the board.
 * 2. flip the tokens.
 * 3. get the scores.
 * 
 * @author Jerry Zhu
 *
 */
public class ReversiController {


	// Declare some instance variables to help us keep track of the game processing.
	private int TABLE_SIZE;

	/**
	 * This is the constructor of the controller which initialize a model.
	 * @param model The recent table.
	 */
	public ReversiController(ReversiModel model) {
		TABLE_SIZE = model.getTableSize();
	}

	/**
	 * This method is to get the placeable positions according to the player.
	 * 
	 * @param player The player we need to find it's placeable locations
	 * @param opponent The player's opponent
	 * @param placeablePositions A hashset that collect all of the placeable positions
	 * @param model The current table of the game
	 * @return The hashset that contains all of the placeable positions
	 */
	public HashSet<Point> getPlaceableLocations(String player, String opponent, HashSet<Point> placeablePositions, ReversiModel model) {
		String[][] table = model.getTable();
		for(int i = 0; i < TABLE_SIZE;i++) {
			for(int j = 0; j < TABLE_SIZE; j++) {
				if(table[i][j] == opponent){
					int I = i, J = j;  
					// There are total of 8 different directions to check if the location is placeable.
					if(i-1>=0 && j-1>=0 && table[i-1][j-1] == "_"){ 
						i = i+1; j = j+1;
						while(i<7 && j<7 && table[i][j] == opponent){i++;j++;}
						if(i<=7 && j<=7 && table[i][j] == player) placeablePositions.add(new Point(I-1, J-1));
					}
					i=I;j=J;
					if(i-1>=0 && table[i-1][j] == "_"){
						i = i+1;
						while(i<7 && table[i][j] == opponent) i++;
						if(i<=7 && table[i][j] == player) placeablePositions.add(new Point(I-1, J));
					} 
					i=I;
					if(i-1>=0 && j+1<=7 && table[i-1][j+1] == "_"){
						i = i+1; j = j-1;
						while(i<7 && j>0 && table[i][j] == opponent){i++;j--;}
						if(i<=7 && j>=0 && table[i][j] == player) placeablePositions.add(new Point(I-1, J+1));
					}  
					i=I;j=J;
					if(j-1>=0 && table[i][j-1] == "_"){
						j = j+1;
						while(j<7 && table[i][j] == opponent)j++;
						if(j<=7 && table[i][j] == player) placeablePositions.add(new Point(I, J-1));
					}
					j=J;
					if(j+1<=7 && table[i][j+1] == "_"){
						j=j-1;
						while(j>0 && table[i][j] == opponent)j--;
						if(j>=0 && table[i][j] == player) placeablePositions.add(new Point(I, J+1));
					}
					j=J;
					if(i+1<=7 && j-1>=0 && table[i+1][j-1] == "_"){
						i=i-1;j=j+1;
						while(i>0 && j<7 && table[i][j] == opponent){i--;j++;}
						if(i>=0 && j<=7 && table[i][j] == player) placeablePositions.add(new Point(I+1, J-1));
					}
					i=I;j=J;
					if(i+1 <= 7 && table[i+1][j] == "_"){
						i=i-1;
						while(i>0 && table[i][j] == opponent) i--;
						if(i>=0 && table[i][j] == player) placeablePositions.add(new Point(I+1, J));
					}
					i=I;
					if(i+1 <= 7 && j+1 <=7 && table[i+1][j+1] == "_"){
						i=i-1;j=j-1;
						while(i>0 && j>0 && table[i][j] == opponent){i--;j--;}
						if(i>=0 && j>=0 && table[i][j] == player)placeablePositions.add(new Point(I+1, J+1));
					}
					i=I;j=J;
				}
			}
		}
		return placeablePositions;
	}
	
	/**
	 * This method will flip the tokens according to the player's side("W" or "B").
	 * 
	 * @param point The point that recently placed on the board.
	 * @param player The recent point's side.
	 * @param opponent The opponent side of the recent point.
	 * @param table The recent board of the game.
	 * @return The new table with the token flip.
	 */
	public String[][] flipTheOpponent(Point point, String player, String opponent, String[][] table) {
		int i = point.getRow();
		int j = point.getCol();
		int I = i, J = j;  

		// There are also 8 different directions, so we need to deal with 8 different cases.
		if(i-1>=0 && j-1>=0 && table[i-1][j-1] == opponent){ 
			i = i-1; j = j-1;
			while(i>0 && j>0 && table[i][j] == opponent){i--;j--;}
			if(i>=0 && j>=0 && table[i][j] == player) {
				while(i!=I-1 && j!=J-1)table[++i][++j]=player;
				}
		} 
		i=I;j=J; 
		if(i-1>=0 && table[i-1][j] == opponent){
			i = i-1;
			while(i>0 && table[i][j] == opponent) i--;
			if(i>=0 && table[i][j] == player) {
				while(i!=I-1)table[++i][j]=player;
				}
		} 
		i=I; 
		if(i-1>=0 && j+1<=7 && table[i-1][j+1] == opponent){
			i = i-1; j = j+1;
			while(i>0 && j<7 && table[i][j] == opponent){i--;j++;}
			if(i>=0 && j<=7 && table[i][j] == player) {
				while(i!=I-1 && j!=J+1)table[++i][--j] = player;
				}
		}   
		i=I;j=J;
		if(j-1>=0 && table[i][j-1] == opponent){
			j = j-1;
			while(j>0 && table[i][j] == opponent)j--;
			if(j>=0 && table[i][j] == player) {
				while(j!=J-1)table[i][++j] = player;
				}
		}
		j=J; 
		if(j+1<=7 && table[i][j+1] == opponent){
			j=j+1;
			while(j<7 && table[i][j] == opponent)j++;
			if(j<=7 && table[i][j] == player) {
				while(j!=J+1)table[i][--j] = player;
				}
		}
		j=J; 
		if(i+1<=7 && j-1>=0 && table[i+1][j-1] == opponent){ 
			i=i+1;j=j-1;
			while(i<7 && j>0 && table[i][j] == opponent){i++;j--;}
			if(i<=7 && j>=0 && table[i][j] == player) {
				while(i!=I+1 && j!=J-1)table[--i][++j] = player;
				}
		}
		i=I;j=J; 
		if(i+1 <= 7 && table[i+1][j] == opponent){ 
			i=i+1;
			while(i<7 && table[i][j] == opponent) i++;
			if(i<=7 && table[i][j] == player) {
				while(i!=I+1)table[--i][j] = player;
				}
		}
		i=I;

		if(i+1 <= 7 && j+1 <=7 && table[i+1][j+1] == opponent){
			i=i+1;j=j+1;
			while(i<7 && j<7 && table[i][j] == opponent){i++;j++;}
			if(i<=7 && j<=7 && table[i][j] == player) {
				while(i!=I+1 && j!=J+1)table[--i][--j] = player;
				}
			}
		return table;
	}
	
	public boolean tableEquals(String[][] table1, String[][] table2) {
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				if(!table1[i][j].equals(table2[i][j])) {
					return false;
				}
			}
		}		
		return true;
	}

}
