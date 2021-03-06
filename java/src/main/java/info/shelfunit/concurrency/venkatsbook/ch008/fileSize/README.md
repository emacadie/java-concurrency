This is another set of files to get the file size of a directory tree.    

The main method is in ConcurrentFileSizeWAkka.java. It starts the Actor system and instantiates SizeCollector.java. It sends a message to SizeCollector with an instance of FileToProcess.java, which is the directory tree we are measuring. It then sends the SizeCollector and the ActorSystem to a method called startFileProcessor, which instantiates a FileProcessor.java, and sends it a message containing the SizeCollector.     

In ConcurrentFileSizeWAkka, we see this line:    
final ActorRef fileProcessor = system.actorOf( Props.create( FileProcessor.class, sizeCollector ) );
The constructor for FileProcessor takes an instance of SizeCollector as its argument, in addition to the class. In the other examples, the instantiation is done with the class and a random string.     


SizeCollector has some variables. One of these is a list of files to process, and a list of FileProcessors.     

SizeCollector can receive three messages:      
A message of type RequestAFile will be send from a FileProcessor. The sender is added to list of idle processors. If there are files to process and any processors available, it pops the list of file names and processors, and sends the file name to the FileProcessor. This happens in a method called sendAFileToProcess.    

A message of type FileToProcess will add the FileToProcess's file name to the list of file names. It will also call sendAFileToProcess, and increments the number of files to visit.    

A message of type FileSize will add its size value to SizeCollector's totalSize variable. It will decrement the number of files to visit. If the number of files to visit is 0, it will print out the total and the time taken.    

FileProcessor can receive a message of FileToProcess. If the message is a file, it will get the size and send it in a FileSize message to the SizeCollector. If it is a directory, it will look at the objects in that directory. If any of them are subdirectories, they will be send as a FileToProcess message to SizeCollector. If any of the objects are files, their sizes are totaled, and the total is sent as a FileSize message to SizeCollector.    

FileToProcess is a class that holds a String with the file name.    

FileSize.java contains a long variable which holds the size of the files in the directory.

RequestAFile.java is an empty class that is only used to send a message.    

