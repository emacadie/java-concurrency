package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import groovyx.gpars.actor.DynamicDispatchActor
import java.io.File;

public class FileProcessorGroovy extends DynamicDispatchActor {

    def sizeCollector;

    public FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector;
        registerToGetFile()
    }

    @Override
    public void preStart() {
        registerToGetFile();
    }

    public void registerToGetFile() {
        // sizeCollector.send( new RequestAFileGroovy() );
        sizeCollector.send( this );
    }

    void onMessage( FileToProcessGroovy message ) {
        // FileToProcess fileToProcess = ( FileToProcess ) message;
        // System.out.println( "here is the fileName: " + ( ( FileToProcess )message ).fileName );
    
        final File file = new File( message.fileName ); // new File( ( ( FileToProcess )message ).fileName );
        long size = 0L;
    
        if ( file.isFile() ) {
            size = file.length();
        } else {
            File[] children = file.listFiles();
            if ( children != null ) {
                for ( File child : children ) {
                    if ( child.isFile() ) { 
                        size += child.length(); 
                    } else {
                        sizeCollector.send( new FileToProcessGroovy( child.getPath() ) );
                    }
                }
            }
        } // if ( file.isFile() )
    
        sizeCollector.send( new FileSizeGroovy( size ) );
        registerToGetFile();
    } // if (message instanceof)
   
    
} // end FileProcessor - line 54

