package MemoryManagement;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreeTest.
 *
 * @author  David Ferrara
 * @version 3.31.2015
 */
public class TreeTest
{
    private Tree t1;
    /**
     * Default constructor for test class TreeTest
     */
    public TreeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        t1 = new Tree(64, null);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetLeft()
    {
        assertEquals(null, t1.getLeft());
    }

    @Test
    public void testSetLeft()
    {
        Tree t2 = new Tree(32, t1);
        assertEquals(null, t1.getLeft());
        t1.setLeft(t2);
        assertEquals(t2, t1.getLeft());
    }
    
    @Test
    public void testGetRight()
    {
        assertEquals(null, t1.getRight());
    }

    @Test
    public void testSetRight()
    {
        Tree t2 = new Tree(32, t1);
        assertEquals(null, t1.getRight());
        t1.setRight(t2);
        assertEquals(t2, t1.getRight());
    }
    
    @Test
    public void testGetSize()
    {
        assertEquals(64, t1.getSize());
    }

    @Test
    public void testSetSize()
    {
        assertEquals(64, t1.getSize());
        t1.setSize(32);
        assertEquals(32, t1.getSize());
    }
    
    @Test
    public void testGetLp()
    {
        assertEquals(null, t1.getLp());
    }

    @Test
    public void testSetLp()
    {
        Process p1 = new Process(8, "Process A");
        assertEquals(null, t1.getLp());
        t1.setLp(p1);
        assertEquals(p1, t1.getLp());
    }
    
    @Test
    public void testGetSizeLeft()
    {
        assertEquals(64, t1.getSizeLeft());
    }
    
    @Test
    public void testFind()
    {
        //Set up the tree.
        Process p1, p2;
        p1 = new Process(32, "Process A");
        p2 = new Process(16, "Process B");
        Tree t2, t3, t4, t5;
        t2 = new Tree(32, t1);
        t3 = new Tree(32, t1);
        t4 = new Tree(16, t3);
        t5 = new Tree(16, t3);
        t1.setLeft(t2);
        t1.setRight(t3);
        t3.setLeft(t4);
        t3.setRight(t5);
        t2.setLp(p1);
        t4.setLp(p2);
        
        //Run tests.
        assertEquals(p1, t2.getLp());
        assertEquals(p2, t4.getLp());
        assertEquals(t2, t1.find(p1));
        assertEquals(t4, t1.find(p2));
    }
    
    @Test
    public void testGenerateChildren()
    {
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
        t1.generateChildren();
        Tree t2 = t1.getLeft();
        Tree t3 = t1.getRight();
        assertEquals(t2, t1.getLeft());
        assertEquals(t3, t1.getRight());
        assertEquals(32, t1.getLeft().getSize());
        assertEquals(32, t1.getRight().getSize());
    }
    
    @Test
    public void testResetChildren()
    {
        testGenerateChildren();
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
    }
    
    @Test
    public void testFindSuitableChild()
    {
        //Set up the tree.
        Process p1, p2;
        p1 = new Process(32, "Process A");
        p2 = new Process(16, "Process B");
        Tree t2, t3, t4, t5;
        t2 = new Tree(32, t1);
        t3 = new Tree(32, t1);
        t4 = new Tree(16, t3);
        t5 = new Tree(16, t3);
        t1.setLeft(t2);
        t1.setRight(t3);
        t3.setLeft(t4);
        t3.setRight(t5);
        t2.setLp(p1);
        
        //Run Tests.
        assertEquals(t4, t1.findSuitableChild(p2));
    }
}

