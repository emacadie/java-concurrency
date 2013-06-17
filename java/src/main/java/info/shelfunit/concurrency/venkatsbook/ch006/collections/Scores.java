package info.shelfunit.concurrency.venkatsbook.ch006.collections;

import scala.concurrent.stm.Ref.*;
import scala.concurrent.stm.japi.STM;
import scala.concurrent.stm.TMap.*;

// standard Scala
import scala.collection.JavaConversions;

import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Scores {

    final private scala.concurrent.stm.TMap.View< String, Integer > scoreValues = STM.newTMap();

    final scala.concurrent.stm.Ref.View< Long > updates = STM.newRef( new Long(0L) ); 

    public void updateScore( final String name, final int score ) {
	STM.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		scoreValues.put( name, score );
		updates.swap( updates.get() + 1 );
		if ( score == 13 ) {
		    throw new RuntimeException( "Reject this score of 13" );
		}
		return null;
	    } // end call
	} );
    } // end updateScore

    public Iterable< String > getNames() {
	return JavaConversions.asJavaIterable(scoreValues.keySet());
    } // end getNames

    public long getNumberOfUpdates() {
	return updates.get();
    }

    public int getScore(final String name) {
	return scoreValues.get(name).get();
    }


} // end class Scores
