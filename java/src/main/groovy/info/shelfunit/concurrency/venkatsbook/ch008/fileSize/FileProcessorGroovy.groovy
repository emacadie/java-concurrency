package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovyx.gpars.actor.DynamicDispatchActor
import java.io.File;

class FileProcessorGroovy extends DynamicDispatchActor {

    def sizeCollector;

    def FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector;
        registerToGetFile()
    }

    def void registerToGetFile() {
        // I cannot get this to work by sending a RequestAFileGroovy object
        // def rafG =  new RequestAFileGroovy()
        // sizeCollector.send( rafG )
        sizeCollector.send( this );
    }

    void onMessage( FileToProcessGroovy message ) {
    
        final File file = new File( message.fileName )
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

