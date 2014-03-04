package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class SizeCollector extends UntypedActor {
    // these are created and initialized upon instantiation
    // a list of files/directories to process
    private final List< String > toProcessFileNames = new ArrayList< String >();
    
    private final List< ActorRef > idleFileProcessors = new ArrayList< ActorRef >();
    private long pendingNumberOfFilesToVisit = 0L;
    private long totalSize = 0L;
    private long start = System.nanoTime();

    public void sendAFileToProcess() {
	if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
	    idleFileProcessors.remove( 0 ).tell( new FileToProcess( toProcessFileNames.remove( 0 ) ), getSelf()  );
	}
    } // sendAFileToProcess

    public void onReceive( final Object message ) {
	
	if ( message instanceof RequestAFile ) {
	    // the sender is a FileProcessor
	    idleFileProcessors.add( getSender() );
	    sendAFileToProcess();
	}
	if ( message instanceof FileToProcess ) {
	    toProcessFileNames.add( ( ( FileToProcess ) message ).fileName );
	    pendingNumberOfFilesToVisit += 1;
	    sendAFileToProcess();
	}

	if ( message instanceof FileSize ) {
	    totalSize += ( ( FileSize )( message ) ).size;
	    pendingNumberOfFilesToVisit -= 1;
	    if ( pendingNumberOfFilesToVisit == 0 ) {
		long end = System.nanoTime();
		System.out.println( "Total size is: " + totalSize );
		System.out.println( "Time taken is: " + ( end - start )/1.0e9  );
		getContext().system().shutdown();
	    }
	}

    } // onReceive
    
} // end SizeCollector

