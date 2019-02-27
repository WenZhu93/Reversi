package model;

/**
 * This method will construct a point object which we can put on the board.
 * @author Jerry Zhu
 *
 */
public class Point {

		// x represents the row and y represents the column.
        private int x, y;
        /**
         * This is the constructor of the point.
         * @param x The row of the point.
         * @param y The column of the point.
         */
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        /**
         * This method is to convert the point to a string.
         * @return The converted string of the point.
         */
        @Override
        public String toString(){
        	String temp = String.valueOf((char)(y+97));
            return temp+""+(x+1);
        }
        
        /**
         * This method is to check if the two points are equal.
         * @return A boolean variable represent if they are equal.
         */
        @Override
        public boolean equals(Object o){
            return o.hashCode()==this.hashCode();
        }

        /**
         * This method is to implement some hash code for each of the points.
         * @return The hash code of the point.
         */
        @Override
        public int hashCode() {
            return Integer.parseInt(x+""+y);
        }
        
        /**
         * This method is to get the row of the recent point.
         * 
         * @return The row of the point.
         */
        public int getRow() {
        	return x;
        }
        
        /**
         * This method is to get the column of the recent point.
         * 
         * @return The column of the point.
         */
        public int getCol() {
        	return y;
        }
}
