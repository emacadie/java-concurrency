package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.File;

public class FileProcessor extends UntypedActor {

    private final ActorRef sizeCollector;

    public FileProcessor( final ActorRef theSizeCollector ) {
        sizeCollector = theSizeCollector;
    }

    @Override
    public void preStart() {
        registerToGetFile();
    }

    public void registerToGetFile() {
        sizeCollector.tell( new RequestAFile(), getSelf() );
    }

    public void onReceive( final Object message ) {

        if ( message instanceof FileToProcess ) {
            FileToProcess fileToProcess = ( FileToProcess ) message;
            // System.out.println( "here is the fileName: " + ( ( FileToProcess )message ).fileName );
        
            final File file = new File( fileToProcess.fileName ); // new File( ( ( FileToProcess )message ).fileName );
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
                            sizeCollector.tell( new FileToProcess( child.getPath() ), getSelf() );
                        }
                    }
                }
            } // if ( file.isFile() )
        
            sizeCollector.tell( new FileSize( size ), getSelf() );
            registerToGetFile();
        } // if (message instanceof)
    } // onReceive
    
} // end FileProcessor

