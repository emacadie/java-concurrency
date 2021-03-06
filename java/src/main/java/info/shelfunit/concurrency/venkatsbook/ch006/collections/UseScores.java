package info.shelfunit.concurrency.venkatsbook.ch006.collections;

import scala.concurrent.stm.Ref.*;

import scala.concurrent.stm.TMap.*;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UseScores {

    public static void main(final String[] args) {
	final Scores scores = new Scores();
	scores.updateScore("Joe", 14 );
	scores.updateScore("Sally", 15 );
	scores.updateScore("Bernie", 12 );
	System.out.println( "Number of updates: " + scores.getNumberOfUpdates() );

	try {
	    scores.updateScore("Bill", 13 );
	} catch ( Exception e ) {
	    System.out.println( "update failed for score 13" );
	}

	System.out.println( "Number of updates: " + scores.getNumberOfUpdates() );

	for (String name : scores.getNames()) {
	    System.out.println( "Score for " + name + " is " + scores.getScore(name) );
	} // end for loop

    } // end main

} // end class UScores
