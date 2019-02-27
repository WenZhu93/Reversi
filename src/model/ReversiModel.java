package model;

/**
 * This class is the model of the Reversi game which is to initialize the
 * table and place the tokens.
 * 
 * @author Jerry Zhu
 *
 */
public class ReversiModel {
	private final int TABLE_SIZE = 8;
	private String[][] table = new String[TABLE_SIZE][TABLE_SIZE];

	/**
	 * This is the constructor of the model which generate a default table.
	 */
	public ReversiModel() {
		// Initialize the table.
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				table[i][j] = "_";
			}
		}
		table[3][3] = "W";
		table[3][4] = "B";
		table[4][3] = "B";
		table[4][4] = "W";
	}
	
	/**
	 * This method is to place a W token on the table at a specific location.
	 * 
	 * @param row The row of the token need to placed.
	 * @param col The column of the token need to placed.
	 */
	public void placeW(int row, int col) {
		table[row][col] = "W";
	}
	
	/**
	 * This method is to place a B token on the table at a specific location.
	 * 
	 * @param row The row of the token need to placed.
	 * @param col The column of the token need to placed.
	 */
	public void placeB(int row, int col) {
		table[row][col] = "B";
	}
	
	/**
	 * This method is to place a * token on the table at a specific location.
	 * 
	 * @param row The row of the token need to placed.
	 * @param col The column of the token need to placed.
	 */
	public void placeStar(int row, int col) {
		table[row][col] = "*";
	}

	/**
	 * This method is to get a token on the table at a specific location.
	 * 
	 * @param row The row location of the token need to get.
	 * @param col The column location of the token need to get.
	 * @return The token at the given location.
	 */
    public String getTokenAt(int row, int col) {
    	String tokenAt = table[row][col];
    	return tokenAt;
    }
    
    /**
     * This method will get a current table.
     * 
     * @return The current table.
     */
    public String[][] getTable(){
    	return table;
    }
    
    /**
     * This method will get the size of the table.
     * 
     * @return The size of the table.
     */
    public int getTableSize() {
    	return TABLE_SIZE;
    }
  
    /**
     * This method will clean the stars on the table.
     */
    public void cleanStars() {
    	for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				if(table[i][j] == "*") {
					table[i][j] = "_";
				}
			}
		}
    }
    
    /**
     * This method will check if the current table is full or not.
     * 
     * @return A boolean variable to represent the status of the table.
     */
    public boolean isFull() {
    	for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				if(table[i][j] == "_") {
					return false;
				}else if(table[i][j] == "*") {
					return false;
				}
			}
		}
    	return true;
    }
}
