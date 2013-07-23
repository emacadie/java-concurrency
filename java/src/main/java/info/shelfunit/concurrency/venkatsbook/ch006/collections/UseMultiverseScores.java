package info.shelfunit.concurrency.venkatsbook.ch006.collections;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UseMultiverseScores {

    public static void main(final String[] args) {
	final MultiverseScores scores = new MultiverseScores();
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

} // end class UseMultiverseScores
