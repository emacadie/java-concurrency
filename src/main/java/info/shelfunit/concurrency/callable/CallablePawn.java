package info.shelfunit.concurrency.callable;

import java.util.List;
import java.util.LinkedList;
import java.util.UUID;

public class CallablePawn {

    public CallablePawn() { 
	System.out.println( "Hello " + UUID.randomUUID().toString() );
    }

    private List< Double > theList = new LinkedList< Double >();
    public List< Double > getTheList() {
	return theList;
    }
    private List< Double > sumsList = new LinkedList< Double >();
    
    private double total = 0;
    private double sum = 0;

    private void populateList() {
	for ( double d = 0; d < 10; d++ ) {
	    theList.add( d + 1 );
	}
    }

    public static void main( String args[] ) {
    } // end method main

} // end class info.shelfunit.concurrency.callable.CallablePawn
