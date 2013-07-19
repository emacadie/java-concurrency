package info.shelfunit.concurrency.venkatsbook.ch009.fileSize

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

@Immutable
class RequestAFile{ }

@Immutable
class FileSize { 
  long size
}

@Immutable
class FileToProcess {
  String fileName
}





