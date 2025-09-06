import static org.junit.Assert.*;
import org.junit.*;

import singly.*;

/*
* This is a Java Test Class, which uses the JUnit package to create
* and run tests
* 
* You can use this to evaluate your code. Examine these tests, as writing
* similar test cases will help you immensly on other Assignments/Labs, as
* well as moving forward in your CS career.
*/
public class LinkedTrainCarsTest {

    @Test
    public void testNumCars() {
        // WRITE YOUR CODE HERE
        LinkedTrainCars test = new LinkedTrainCars();

    }

    @Test
    public void testInsertAt() {
        // WRITE YOUR CODE HERE
        LinkedTrainCars test = new LinkedTrainCars();
        test.insertAt("item1",1); 
        test.insertAt("item2",2);
        test.insertAt("item3",3);
        test.insertAt("item4",2);
        test.insertAt("item5",4);

        assertEquals(1, test.insertAt("item1", 1));
        assertEquals("item4", "item1");
        assertEquals("item2", "item1");
        assertEquals("item5", "item1");
        assertEquals("item3", "item1");

    }

}