package tests;


import controller.ReversiController;
import model.Point;
import model.ReversiModel;
import model.UnivalidPointException;
import model.UnvalidInputException;
import view.ReversiView;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;



public class ReversiControllerTest {

	@Test
	void testGetPlaceableLocations() {
		ReversiModel board = new ReversiModel();
		ReversiController controller = new ReversiController(board);
		HashSet<Point> placeablePositions = new HashSet<Point>();
		HashSet<Point> positions = new HashSet<Point>();
		Point point = new Point(4, 2);
		positions.add(point);
		point = new Point(5,3);
		positions.add(point);
		point = new Point(3,5);
		positions.add(point);
		point = new Point(2,4);
		positions.add(point);
		assertEquals(controller.getPlaceableLocations("W", "B", placeablePositions, board), positions);
		placeablePositions.clear();
		positions.clear();

		board = new ReversiModel();
		controller = new ReversiController(board);
		board.placeB(0, 0);
		board.placeB(0, 7);
		board.placeB(7, 0);
		board.placeB(7, 7);
		board.placeW(0, 4);
		board.placeW(7, 0);
		board.placeW(4, 7);
		board.placeW(7, 4);

		board.placeW(4, 2);
		board.placeW(4, 3);
		board.placeB(3, 2);
		board.placeB(3, 3);
		point = new Point(2, 1);
		positions.add(point);
		point = new Point(2,2);
		positions.add(point);
		point = new Point(2,3);
		positions.add(point);
		point = new Point(2,4);
		positions.add(point);
		point = new Point(2,5);
		positions.add(point);
		assertEquals(controller.getPlaceableLocations("W", "B", placeablePositions, board), positions);

		board = new ReversiModel();
		controller = new ReversiController(board);
		board.placeB(0, 0);
		board.placeB(0, 7);
		board.placeB(7, 0);
		board.placeB(7, 7);
		board.placeB(0, 4);
		board.placeB(7, 0);
		board.placeB(4, 7);
		board.placeB(7, 4);

		board.placeB(3, 3);
		board.placeB(2, 3);
		board.placeW(2, 4);
		board.placeW(3, 4);
		point = new Point(1, 2);
		positions.add(point);
		point = new Point(2,2);
		positions.add(point);
		point = new Point(3,2);
		positions.add(point);
		point = new Point(4,2);
		positions.add(point);
		point = new Point(5,2);
		positions.add(point);
		assertEquals(controller.getPlaceableLocations("W", "B", placeablePositions, board), positions);

		board = new ReversiModel();
		controller = new ReversiController(board);
		board.placeW(0, 0);
		board.placeB(1, 0);
		board.placeB(1, 6);
		board.placeB(6, 0);
		board.placeB(6, 6);
		board.placeB(1, 4);
		board.placeB(6, 1);
		board.placeB(4, 6);
		board.placeB(1, 4);

		board.placeB(3, 3);
		board.placeB(3, 2);
		board.placeW(5, 3);
		board.placeW(6, 3);
		point = new Point(2, 1);
		positions.add(point);
		point = new Point(2,2);
		positions.add(point);
		point = new Point(2,3);
		positions.add(point);
		point = new Point(2,4);
		positions.add(point);
		point = new Point(2,5);
		positions.add(point);
		point = new Point(2,0);
		positions.add(point);
		assertEquals(controller.getPlaceableLocations("W", "B", placeablePositions, board), positions);


		board = new ReversiModel();
		controller = new ReversiController(board);
		board.placeB(3, 3);
		board.placeB(4, 4);
		board.placeW(3, 4);
		board.placeW(4, 3);
		board.placeW(2, 2);
		point = new Point(2,3);
		positions.add(point);
		point = new Point(3,2);
		positions.add(point);
		point = new Point(4,5);
		positions.add(point);
		point = new Point(5,4);
		positions.add(point);
		point = new Point(5,5);
		positions.add(point);
		assertEquals(controller.getPlaceableLocations("W", "B", placeablePositions, board), positions);
	}

	@Test
	void testFlipTheOpponent() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		Point point;
		// Eight directions
		String[][] table = {
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "W", "W", "W", "W", "W", "_", "_"},
				{"_", "W", "W", "W", "W", "W", "_", "_"},
				{"_", "W", "W", "W", "W", "W", "_", "_"},
				{"_", "W", "W", "W", "W", "W", "_", "_"},
				{"_", "W", "W", "W", "W", "W", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
		};
		// inner circle
		model.placeB(3,2);
		model.placeB(3,3);
		model.placeB(3,4);
		model.placeB(4,2);
		model.placeB(4,4);
		model.placeB(5,2);
		model.placeB(5,3);
		model.placeB(5,4);
		// outer circle
		model.placeW(2,1);
		model.placeW(2,2);
		model.placeW(2,3);
		model.placeW(2,4);
		model.placeW(2,5);
		model.placeW(3,1);
		model.placeW(3,5);
		model.placeW(4,1);
		model.placeW(4,5);
		model.placeW(5,1);
		model.placeW(5,5);
		model.placeW(6,1);
		model.placeW(6,2);
		model.placeW(6,3);
		model.placeW(6,4);
		model.placeW(6,5);

		//place a W at the center.
		model.placeW(4,3);
		point = new Point(4,3);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table));
		table[0][0] = "W";
		assertFalse(controller.tableEquals(model.getTable(), table));


		model = new ReversiModel();
		controller = new ReversiController(model);
		String[][] table1 = {
				{"W", "_", "_", "_", "_", "_", "W", "W"},
				{"W", "B", "_", "_", "_", "_", "B", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "W", "B", "_", "_", "_"},
				{"_", "_", "_", "B", "W", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "B", "_", "_", "_", "_", "B", "W"},
				{"W", "W", "_", "_", "_", "_", "_", "W"},
		};
		//place a W at the center.
		model.placeW(0,0);
		model.placeW(0,7);
		model.placeW(7,0);
		model.placeW(7,7);

		model.placeW(1,0);
		model.placeW(0,6);
		model.placeW(7,1);
		model.placeW(6,7);

		model.placeB(1,1);
		model.placeB(1,6);
		model.placeB(6,1);
		model.placeB(6,6);

		point = new Point(0,0);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(0,7);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(7,0);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(7,7);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));

		point = new Point(1,0);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(0,6);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(7,1);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));
		point = new Point(6,7);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table1));


		model = new ReversiModel();
		controller = new ReversiController(model);
		// Eight directions
		String[][] table2 = {
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
				{"W", "W", "W", "W", "W", "W", "W", "_"},
		};
		// inner circle
		model.placeB(3,2);
		model.placeB(3,3);
		model.placeB(3,4);
		model.placeB(4,2);
		model.placeB(4,4);
		model.placeB(5,2);
		model.placeB(5,3);
		model.placeB(5,4);
		model.placeB(2,1);
		model.placeB(2,2);
		model.placeB(2,3);
		model.placeB(2,4);
		model.placeB(2,5);
		model.placeB(3,1);
		model.placeB(3,5);
		model.placeB(4,1);
		model.placeB(4,5);
		model.placeB(5,1);
		model.placeB(5,5);
		model.placeB(6,1);
		model.placeB(6,2);
		model.placeB(6,3);
		model.placeB(6,4);
		model.placeB(6,5);
		// others
		model.placeW(2,2);
		model.placeW(3,1);
		model.placeW(2,4);
		model.placeW(3,5);
		model.placeW(5,1);
		model.placeW(6,2);
		model.placeW(5,5);
		model.placeW(6,4);
		// outer circle
		model.placeW(1,0);
		model.placeW(1,1);
		model.placeW(1,2);
		model.placeW(1,3);
		model.placeW(1,4);
		model.placeW(1,5);
		model.placeW(1,6);
		model.placeW(2,0);
		model.placeW(2,6);
		model.placeW(3,0);
		model.placeW(3,6);
		model.placeW(4,0);
		model.placeW(4,6);
		model.placeW(5,0);
		model.placeW(5,6);
		model.placeW(6,0);
		model.placeW(6,6);
		model.placeW(7,0);
		model.placeW(7,1);
		model.placeW(7,2);
		model.placeW(7,3);
		model.placeW(7,4);
		model.placeW(7,5);
		model.placeW(7,6);
		//place a W at the center.
		model.placeW(4,3);
		point = new Point(4,3);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table2));



		model = new ReversiModel();
		controller = new ReversiController(model);
		String[][] table3 = {
				{"B", "_", "_", "_", "_", "_", "B", "B"},
				{"B", "W", "_", "_", "_", "_", "W", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "W", "B", "_", "_", "_"},
				{"_", "_", "_", "B", "W", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "W", "_", "_", "_", "_", "W", "B"},
				{"B", "B", "_", "_", "_", "_", "_", "B"},
		};
		model.placeB(0,0);
		model.placeB(0,7);
		model.placeB(7,0);
		model.placeB(7,7);
		model.placeB(1,0);
		model.placeB(0,6);
		model.placeB(7,1);
		model.placeB(6,7);

		model.placeW(1,1);
		model.placeW(1,6);
		model.placeW(6,1);
		model.placeW(6,6);

		point = new Point(1,1);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table3));
		point = new Point(1,6);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table3));
		point = new Point(6,1);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table3));
		point = new Point(6,6);
		controller.flipTheOpponent(point, "W", "B", model.getTable());
		assertTrue(controller.tableEquals(model.getTable(), table3));
	}
	
	
	
	@Test
	void testModel() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		Point point = new Point(0,0);
		Point test = new Point(0,0);
		assertTrue(point.equals(test));
		test = new Point(0,1);
		assertFalse(point.equals(test));
		String str = point.toString();
		assertEquals(str, "a1");
		String[][] table = {
				{"*", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "W", "B", "_", "_", "_"},
				{"_", "_", "_", "B", "W", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
				{"_", "_", "_", "_", "_", "_", "_", "_"},
		};
		model.placeStar(0,0);
		String token = model.getTokenAt(0, 0);
		assertTrue(controller.tableEquals(model.getTable(), table));
		assertEquals(token, "*");
		model.cleanStars();
		table[0][0] = "_";
		assertTrue(controller.tableEquals(model.getTable(), table));
		assertFalse(model.isFull());
		model.placeW(0, 0);
		model.placeW(0, 1);
		model.placeW(0, 2);
		model.placeW(0, 3);
		model.placeW(0, 4);
		model.placeW(0, 5);
		model.placeW(0, 6);
		model.placeW(0, 7);
		
		model.placeW(1, 0);
		model.placeW(1, 1);
		model.placeW(1, 2);
		model.placeW(1, 3);
		model.placeW(1, 4);
		model.placeW(1, 5);
		model.placeW(1, 6);
		model.placeW(1, 7);

		model.placeW(2, 0);
		model.placeW(2, 1);
		model.placeW(2, 2);
		model.placeW(2, 3);
		model.placeW(2, 4);
		model.placeW(2, 5);
		model.placeW(2, 6);
		model.placeW(2, 7);

		model.placeW(3, 0);
		model.placeW(3, 1);
		model.placeW(3, 2);
		model.placeW(3, 3);
		model.placeW(3, 4);
		model.placeW(3, 5);
		model.placeW(3, 6);
		model.placeW(3, 7);
		
		model.placeW(4, 0);
		model.placeW(4, 1);
		model.placeW(4, 2);
		model.placeW(4, 3);
		model.placeW(4, 4);
		model.placeW(4, 5);
		model.placeW(4, 6);
		model.placeW(4, 7);
		
		model.placeW(5, 0);
		model.placeW(5, 1);
		model.placeW(5, 2);
		model.placeW(5, 3);
		model.placeW(5, 4);
		model.placeW(5, 5);
		model.placeW(5, 6);
		model.placeW(5, 7);

		model.placeW(6, 0);
		model.placeW(6, 1);
		model.placeW(6, 2);
		model.placeW(6, 3);
		model.placeW(6, 4);
		model.placeW(6, 5);
		model.placeW(6, 6);
		model.placeW(6, 7);
		
		model.placeW(7, 0);
		model.placeW(7, 1);
		model.placeW(7, 2);
		model.placeW(7, 3);
		model.placeW(7, 4);
		model.placeW(7, 5);
		model.placeW(7, 6);
		model.placeW(7, 7);
		assertTrue(model.isFull());
		model.placeStar(0, 0);
		assertFalse(model.isFull());
	}
}
