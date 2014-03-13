This is a refactoring of the file size program done in Akka in chapter 8. I also refactored it with GPars as I was making these notes. I forgot that he did it in chapter 9. I will not go over the program because I did that in chapter 8 in the Java section.    

It is kind of interesting that he mixes DefaultActors and DynamicDispatchActors. I do not like the nested DefaultActors. It can get a bit hairy very quickly. I think somewhere on the GPars site it mentions that DynamicDispatchActors are faster. This implementation is slower that the one that I did in GPars in chapter 8 and the Akka version as well.    

DynamicDispatchActors for the win.    

FileProcessor.groovy   
FileSizeFinder.groovy   
findFileSize.groovy   
SizeCollector.groovy   

