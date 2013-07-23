package info.shelfunit.concurrency.venkatsbook.ch006.collections;

import java.util.concurrent.Callable;

import org.multiverse.api.StmUtils;
import org.multiverse.api.collections.TxnMap;
import org.multiverse.api.references.TxnLong;
import org.multiverse.api.Txn;

// from Programming Concurrency on the JVM by Venkat Subramaniam
// this will not run. I get a "ToDoException"

public class MultiverseScores {

    final private TxnMap< String, Integer > scoreValues = StmUtils.newTxnHashMap();

    final TxnLong updates = StmUtils.newTxnLong( new Long(0L) ); 

    public MultiverseScores() {
	
    } // end constructor
    
    public void updateScore( final String name, final int score ) {
	StmUtils.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		scoreValues.put( name, score );
		updates.set( updates.get() + 1 );
		if ( score == 13 ) {
		    throw new RuntimeException( "Reject this score of 13" );
		}
		return null;
	    } // end call
	} );
    } // end updateScore
    
    // I get a "ToDoException" here
    public Iterable< String > getNames() {
	Txn txn = scoreValues.getStm().newDefaultTxn();
	return scoreValues.keySet(txn);
    } // end getNames
    
    public long getNumberOfUpdates() {
	Txn txn = updates.getStm().newDefaultTxn();
	return updates.get(txn);
    }

    public int getScore(final String name) {
	Txn txn = scoreValues.getStm().newDefaultTxn();
	return scoreValues.get(txn, name);
    }

} // end class MultiverseScores
