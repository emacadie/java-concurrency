This is another set of files to get the file size of a directory tree. It is a refactoring of the Akka code in Chapter 8.    

I prefer extending the DynamicDispatchActor over DefaultActor. Some would say that DefaultActor is more idiomatic Groovy. Groovy DSLs are nice, but sometimes the indentation can get pretty crazy. You go three or four or more levels in and it looks a bit crazy. Plus it can just be hard to keep track of stuff in my opinion.    

The main method is in ConcurrentFileSizeWAkka.java. It starts the Actor system and instantiates SizeCollector.java. It sends a message to SizeCollector with an instance of FileToProcess.java, which is the directory tree we are measuring. It then sends the SizeCollector and the ActorSystem to a method called startFileProcessor, which instantiates a FileProcessor.java, and sends it a message containing the SizeCollector.     

In ConcurrentFileSizeWAkka, we see this line:    
final ActorRef fileProcessor = system.actorOf( Props.create( FileProcessor.class, sizeCollector ) );
The constructor for FileProcessor takes an instance of SizeCollector as its argument, in addition to the class. In the other examples, the instantiation is done with the class and a random string.     


SizeCollector has some variables. One of these is a list of files to process, and a list of FileProcessors.     

SizeCollector can receive three messages:      
A message of type RequestAFile will be send from a FileProcessor. The sender is added to list of idle processors. If there are files to process and any processors available, it pops the list of file names and processors, and sends the file name to the FileProcessor. This happens in a method called sendAFileToProcess.    

A message of type FileToProcess will add the FileToProcess's file name to the list of file names. It will also call sendAFileToProcess, and increments the number of files to visit.    

A message of type FileSize will add its size value to SizeCollector's totalSize variable. It will decrement the number of files to visit. If the number of files to visit is 0, it will print out the total and the time taken.    


FileProcessor
   public void onReceive( final Object message ) {

   final FileToProcess fileToProcess = ( FileToProcess ) message;
   // orig: final File file = new File( fileToProcess.fileName );
   if ( message instanceof FileToProcess ) {
       // System.out.println( "here is the fileName: " + ( ( FileToProcess )message ).fileName );
       }
       final File file = new File( fileToProcess.fileName );
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
										    // } // if (message instanceof)
    } // onReceive

This is supposed to by multithreaded, but I only create one instance of FileProcessor. Did I do something wrong? I should create a factory when I have some connectivity.    

FileToProcess is a class that holds a String with the file name.    

FileSize.java contains a long variable which holds the size of the files in the directory.

RequestAFile.java is an empty class that is only used to send a message.    

