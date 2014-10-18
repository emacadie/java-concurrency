package info.shelfunit.concurrency.venkatsbook.ch008.csFileSize;

import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.DynamicDispatchActor
import groovyx.gpars.actor.StaticDispatchActor
import groovy.transform.CompileStatic

import java.io.File;

@CompileStatic
class FileProcessorGroovy extends DynamicDispatchActor {

    SizeCollectorGroovy sizeCollector;

    def FileProcessorGroovy( SizeCollectorGroovy theSizeCollector ) {
        sizeCollector = theSizeCollector;
        registerToGetFile()
    }

    def void registerToGetFile() {
        sizeCollector.send( new RequestAFileGroovy( this ) )
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
    } // onMessage
   
    
} // end class FileProcessorGroovy extends DynamicDispatchActor - line 54

/*
class FileProcessorGroovy extends DefaultActor {
    
    def sizeCollector
    
    public FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector
    }
    
    void afterStart() {
        registerToGetFile()
    }
    
    void registerToGetFile() {
        sizeCollector.send( new RequestAFileGroovy() )
    }
    
    void act() {
        loop {
            react { message ->
                switch ( message ) {
                    case FileToProcessGroovy:
                        def file = new File( message.fileName )
                        def size = 0
                        if ( !file.isDirectory() ) {
                            size = file.length()
                        } else {
                            def children = file.listFiles()
                            if ( children != null ) {
                                children.each { child ->
                                    if ( child.isFile() ) {
                                        size += child.length()
                                    } else {
                                        sizeCollector.send( new FileToProcessGroovy( child.path ) )
                                    }
                                }
                            }
                            sizeCollector.send( new FileSizeGroovy( size ) )
                            registerToGetFile()
                        }
                        break
                }
            } // end react
        } // end loop
    } // end act
} // end class FileProcessorGroovy extends DefaultActor
*/

/*
class FileProcessorGroovy extends StaticDispatchActor< FileToProcessGroovy > {

    def sizeCollector;

    def FileProcessorGroovy( theSizeCollector ) {
        sizeCollector = theSizeCollector;
        registerToGetFile()
    }

    def void registerToGetFile() {
        sizeCollector.send( new RequestAFileGroovy( this ) )
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
    } // onMessage
   
} // end class FileProcessorGroovy extends StaticDispatchActor - line 54
*/
