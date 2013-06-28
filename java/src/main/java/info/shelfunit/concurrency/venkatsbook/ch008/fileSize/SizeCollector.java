package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SizeCollector extends UntypedActor {

    private final List< String > toProcessFileNames = new ArrayList< String >();
    private final List< ActorRef > idleFileProcessors = new ArrayList< ActorRef >();
    private long pendingNumberOfFilesToVisit = 0L;
    private long totalSize = 0L;
    private long start = System.nanoTime();

    public void sendAFileToProcess() {
	if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
	    idleFileProcessors.remove(0).tell( new FileToProcess( toProcessFileNames.remove(0)), getSelf()  );
	}
    } // sendAFileToProcess

    public void onReceive( final Object message ) {
    } // onReceive
    
} // end SizeCollector

