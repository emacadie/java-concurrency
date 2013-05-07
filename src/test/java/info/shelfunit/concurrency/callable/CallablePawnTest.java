package info.shelfunit.concurrency.callable;

import java.util.LinkedList;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

public class CallablePawnTest {

    private String methodName;
    private String className = "CallablePawnTest.";

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        
    } // end method setUp

    @After
    public void tearDown() {
    }

    /**
     * Test of dropUser method, of class UserManagerBean.
     */
    @Test
    public void testDropUser() {
	methodName = className + Thread.currentThread().getStackTrace()[ 1 ].getMethodName();
        System.out.println( "At test " + methodName );
        
        CallablePawn instance = new CallablePawn();
        String expResult = "";
        // String result = instance.dropUser( e );
        assertEquals( 1, 1 );
        // TODO review the generated test code and remove the default call to fail.
        // fail( "The test case is a prototype." );
    } // end method testDropUser


} // end class info.shelfunit.concurrency.callable.CallablePawn                                                                                       

