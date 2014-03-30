package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovy.transform.Immutable

/*
@Immutable
class RequestAFileGroovy {
    int placeHolder
} // end RequestAFile
*/
/*
final class RequestAFileGroovy {
    final Integer placeHolder
    
    RequestAFileGroovy( arg ) {
        placeHolder = arg
    }
    
    // setFileProcessorGroovy( arg ) { }

} // end RequestAFile
*/


final class RequestAFileGroovy {
    final FileProcessorGroovy fileProcessorGroovy
    
    RequestAFileGroovy( arg ) {
        fileProcessorGroovy = arg
    }
    
    // setFileProcessorGroovy( arg ) { }

} // end RequestAFile


