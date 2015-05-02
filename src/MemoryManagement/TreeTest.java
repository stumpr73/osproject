package MemoryManagement;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The test class TreeTest.
 *
 * @author  David Ferrara
 * @version 5.2.2015
 */
public class TreeTest
{
    private Tree t1;
    private ArrayList<Tree> leaves;
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
        leaves = new ArrayList<Tree>();
        t1 = new Tree(64, null, 0, leaves);
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
    public void testGetLeft()
    {
        assertEquals(null, t1.getLeft());
    }

    @Test
    public void testSetLeft()
    {
        Tree t2 = new Tree(32, t1, 0, leaves);
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
        Tree t2 = new Tree(32, t1, 0, leaves);
        assertEquals(null, t1.getRight());
        t1.setRight(t2);
        assertEquals(t2, t1.getRight());
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
    public void testGetLowEnd()
    {
        assertEquals(0, t1.getLowEnd());
    }

    @Test
    public void testSetLowEnd()
    {
        assertEquals(0, t1.getLowEnd());
        t1.setLowEnd(32);
        assertEquals(32, t1.getLowEnd());
    }

    @Test
    public void testGetSizeLeft()
    {
        assertEquals(64, t1.getSizeLeft());
    }

    @Test
    public void testSetSizeLeft()
    {
        assertEquals(64, t1.getSizeLeft());
        t1.setSizeLeft(32);
        assertEquals(32, t1.getSizeLeft());
    }

    @Test
    public void testGetParent()
    {
        assertEquals(null, t1.getParent());
    }

    @Test
    public void testSetParent()
    {
        Tree t2 = new Tree(32, null, 0, leaves);
        assertEquals(null, t2.getParent());
        t2.setParent(t1);
        assertEquals(t1, t2.getParent());
    }

    @Test
    public void testHasParent()
    {
        Tree t2 = new Tree(32, null, 0, leaves);
        assertEquals(false, t2.hasParent());
        t2.setParent(t1);
        assertEquals(true, t2.hasParent());
    }

    @Test
    public void testGenerateChildren()
    {
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
        t1.generateChildren();
        assertNotNull(t1.getLeft());
        assertNotNull(t1.getRight());
        assertEquals(32, t1.getLeft().getSize());
        assertEquals(32, t1.getRight().getSize());
        assertEquals(t1, t1.getLeft().getParent());
        assertEquals(t1, t1.getRight().getParent());
        assertEquals(0, t1.getLeft().getLowEnd());
        assertEquals(32, t1.getRight().getLowEnd());
    }

    @Test
    public void testResetChildren()
    {
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
        t1.generateChildren();
        assertNotNull(t1.getLeft());
        assertNotNull(t1.getRight());
        t1.resetChildren();
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
    }
    
    @Test
    public void testAreNoChildren()
    {
        assertEquals(null, t1.getLeft());
        assertEquals(null, t1.getRight());
        assertEquals(true, t1.areNoChildren());
        t1.generateChildren();
        assertNotNull(t1.getLeft());
        assertNotNull(t1.getRight());
        assertEquals(false, t1.areNoChildren());
    }
    
    @Test
    public void testFindSuitableChild()
    {
        Process p1 = new Process(32, "Process A");
        Process p2 = new Process(16, "Process B");
        Process p3 = new Process(16, "Process C");
        Process p4 = new Process(8, "Process D");
        t1.generateChildren();
        t1.getRight().generateChildren();
    }
    
    @Test
    public void testAssignProcess()
    {
        
    }
    
    @Test
    public void testUpdateParentSize()
    {
        
    }

    @Test
    public void testAllocate()
    {
        Process p1 = new Process(32, "Process A");
        Process p2 = new Process(16, "Process B");
        t1.allocate(p1);
        assertNotNull(t1.getLeft());
        assertEquals(p1, t1.getLeft().getLp());
        t1.allocate(p2);
        assertNotNull(t1.getRight().getLeft());
        assertEquals(p2, t1.getRight().getLeft().getLp());
    }

    @Test
    public void testDeallocate()
    {
        Process p1 = new Process(32, "Process A");
        Process p2 = new Process(16, "Process B");
        t1.allocate(p1);
        t1.allocate(p2);
        t1.deallocate();
        assertNull(t1.getLeft().getLp());
        t1.deallocate();
        assertNull(t1.getLeft());
        assertNull(t1.getRight());
    }
}
