import static org.junit.Assert.*;
import org.junit.*;

import queue.*;

/*
* This is a JUnit Test Class, which uses the JUnit package to create
* and run tests (just like in Lab 2)
* 
* You can use this to evaluate your code. Examine these tests, as writing
* similar test cases will help you immensly on other Assignments/Labs, as
* well as moving forward in your CS career.
*
* You WILL NOT SUBMIT THIS CLASS. This is for your own testing purposes only.
*/
public class QueueTest {

    @Test
    public void testEnqueue() {
        ThemeParkQueue test = new ThemeParkQueue();
        test.enqueue("toAdd");
        assertEquals(1, test.getLineLength());
        assertEquals(2, test.getLineLength());
        assertEquals(3, test.getLineLength());
        assertEquals(4, test.getLineLength());
        assertEquals(5, test.getLineLength());

    }

    @Test
    public void testDequeue() {
        ThemeParkQueue test = new ThemeParkQueue();
        test.dequeue();
        assertEquals("toAdd", test.dequeue());
    }
}