This is another set of files to get the file size of a directory tree.    

The main method is in ConcurrentFileSizeWAkka.java. It starts the Actor system and instantiates SizeCollector.java. It sends a message to SizeCollector with an instance of FileToProcess.java, which is the directory tree we are measuring. It then sends the SizeCollector and the ActorSystem to a method called startFileProcessor, which instantiates a FileProcessor.java, and sends it a message containing the SizeCollector.     

FileSize.java


RequestAFile.java

