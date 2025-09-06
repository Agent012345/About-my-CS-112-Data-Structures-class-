package test;

import static org.junit.Assert.*;
import org.junit.*;

import island.*;
import island.constants.Color;

/**
 * This is a JUnit test class, used to test code
 * 
 * You should test the SmartCat class by designing Island test cases
 * and filling in the JUnit tests according to the assignment
 * description.
 * 
 * @author Colin Sullivan
 */
public class SmartCatTest {

    public static Island pathIsland = new Island(new String[][] {
            {"L", "W", "W", "W", "W", "L", "L", "L", "L"},
            {"L","W", "W", "W", "W", "L", "W", "W", "W"},
            {"L", "L", "W", "W", "W", "L", "W", "W", "W"},
            {"W", "L", "W", "W", "W", "L", "W", "W", "W"},
            {"W", "L", "W", "W", "W", "L", "W", "W", "W"},
            {"W", "L", "W", "W", "L", "L", "W", "W", "W"},
            {"W", "L", "W", "L", "L", "W", "W", "W", "W"},
            {"W","L", "W", "L", "W", "W", "W", "W", "W"},
            {"W", "L", "L", "L", "W", "W", "W", "W", "W"}
    });

    public static Island yarnIsland = new Island(new String[][] {
        {"L", "W", "W", "Y", "L", "L", "L", "Y", "L", "L", "L", "L", "L", "L", "Y"},
        {"L","W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "W", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "L", "W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "W", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "Y", "L", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L","W", "W", "W", "W", "W", "L", "Y", "L", "W", "W", "W", "W", "W", "W"},
        {"Y", "W", "W", "W", "W", "W", "W", "W", "L", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "W", "W", "W", "W", "W", "L", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "L", "W", "W", "W", "W", "W", "L", "Y", "L", "W", "W", "W", "W"},
        {"W", "W", "L", "L", "Y", "L", "L", "W", "W", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "L", "L", "L", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "L", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "L", "Y", "L", "W", "W", "W", "W"}
    });

    public static Island mazeIsland = new Island(new String[][] {
        {"L", "W", "W", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L"},
        {"L","W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "W", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "L", "W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "W", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "L", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "L", "L", "L", "L", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"L","W", "W", "W", "W", "W", "L", "L", "L", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "W", "W", "W", "W", "W", "L", "W", "W", "W", "W", "W", "W"},
        {"L", "W", "W", "W", "W", "W", "W", "W", "L", "W", "W", "W", "W", "W", "W"},
        {"L", "L", "L", "W", "W", "W", "W", "W", "L", "L", "L", "W", "W", "W", "W"},
        {"W", "W", "L", "L", "L", "L", "L", "W", "W", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "L", "L", "L", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "L", "W", "L", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "L", "L", "L", "W", "W", "W", "W"}
    });

    @Test
    public void testWalkPath() {
        SmartCat testCat1 = new SmartCat("TestCat1", pathIsland, 0, 0, Color.WHITE);
        testCat1.walkPath();

        assertEquals(8, pathIsland.getTiles()[0][8].cat.getCol());
        assertEquals(0, pathIsland.getTiles()[0][8].cat.getRow());

    }

    @Test
    public void testCollectAllYarn() {
        SmartCat testCat2 = new SmartCat("TestCat2", yarnIsland, 0, 0, Color.GREY);
        testCat2.collectAllYarn();

        for (int i = 0; i < yarnIsland.getTiles().length; i++){
            for (int j = 0; j < yarnIsland.getTiles()[i].length; j++){
                assertFalse(yarnIsland.getTiles()[i][j].hasYarn);
            }

        }
    }

    @Test
    public void testSolveMaze() {
        SmartCat testCat3 = new SmartCat("TestCat3", mazeIsland, 0, 0, Color.BROWN);
        testCat3.solveMaze();

        assertEquals(14, mazeIsland.getTiles()[0][14].cat.getCol());
        assertEquals(0, mazeIsland.getTiles()[0][14].cat.getRow());

        int numOfLandTiles = 0;
        for (int i = 0; i < mazeIsland.getTiles().length; i++){
            for (int j = 0; j < mazeIsland.getTiles()[i].length; j++){
                if (mazeIsland.getTiles()[i][j].type.equals(Tile.LAND)){
                    numOfLandTiles = numOfLandTiles + 1;
                }
            }
        }
        assertEquals(numOfLandTiles, mazeIsland.getTiles()[0][14].cat.numStepsTaken());

    }

}
