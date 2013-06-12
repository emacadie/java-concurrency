package info.shelfunit.concurrency.callable;

import java.util.List;
import java.util.LinkedList;
import java.util.UUID;

public class CallablePawn {

    public CallablePawn() { 
	System.out.println( "Hello " + UUID.randomUUID().toString() );
	this.populateTheList();
    }

    private List< Double > theList = new LinkedList< Double >();
    public List< Double > getTheList() {
	return theList;
    }
    private List< Double > sumsList = new LinkedList< Double >();
    
    private double total = 0;
    private double sum = 0;
    private double iterations = 10;

    private void populateTheList() {
	for ( double d = 0; d < 10; d++ ) {
	    theList.add( d + 1 );
	}
    }

    public void getTheSumGoingForward() {
	total = 0;
	
	for ( double q = 0; q <= 10; q++ ) {
	    total += this.getSubSum( q );
	}
	System.out.println( "From getTheSumGoingForward(), the total is: " + total );
    }

    public void getTheSumGoingBackward() {
	total = 0;
	for ( double q = 10; q > 0; q-- ) {
	    total += this.getSubSum( q );
	}
	System.out.println( "From getTheSumGoingBackward(), the total is: " + total );
    }

    private double getSubSum( double arg ) {
	System.out.println( "Called getSubSum with arg: " + arg );
	double subSum = 0;
	for ( double i = 0; i <= 100; i++ ) {
	    subSum += i *  arg;
	}
	return subSum;
    }

    public static void main( String args[] ) {
	CallablePawn cPawn = new CallablePawn();
    } // end method main

} // end class info.shelfunit.concurrency.callable.CallablePawn
