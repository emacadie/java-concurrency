package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovyx.gpars.actor.DynamicDispatchActor
import java.io.File;

public class FileProcessorGroovy extends DynamicDispatchActor {

    def sizeCollector;

    public FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector;
        registerToGetFile()
    }

    public void registerToGetFile() {
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

