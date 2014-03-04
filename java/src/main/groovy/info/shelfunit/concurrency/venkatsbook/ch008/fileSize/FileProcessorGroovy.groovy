package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovyx.gpars.actor.DynamicDispatchActor

import java.io.File;

public class FileProcessorGroovy extends DynamicDispatchActor {

    def sizeCollector;
    
    void setSizeCollector( sc ) {  }
    
    public FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector;
        println( "sizeCollector is a ${sizeCollector.getClass().getName()}" )
        registerToGetFile()
    }

    void registerToGetFile() {
        sizeCollector.send( this ) // new RequestAFileGroovy() );
    }

    void onMessage( FileToProcessGroovy ftpG ) {
        
        println( "here is the fileName: " + ftpG.fileName );
    
        final File file = new File( ftpG.fileName ); // new File( ( ( FileToProcess )message ).fileName );
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
                        ftpG = new FileToProcessGroovy( child.getPath() )
                        // ftpG.parallelGroup = this.parallelGroup
                        sizeCollector.send( ftpG );
                    }
                }
            }
        } // if ( file.isFile() )
    
        sizeCollector.send( new FileSizeGroovy( size ) );
        registerToGetFile();
      
    } // onMessage
    
} // end FileProcessorGroovy - line 54

