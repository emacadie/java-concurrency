package info.shelfunit.concurrency.venkatsbook.ch009

import groovyx.gpars.actor.Actors
import groovyx.gpars.dataflow.DataflowVariable
import static groovyx.gpars.dataflow.Dataflow.task
import java.util.concurrent.TimeUnit
// from Programming Concurrency on the JVM by Venkat Subramaniam

class DataFlow001 { 

  def fetchContent(String url, DataflowVariable content) { 
    println("Requesting data from $url")
    // same as content.bind(url.toURL().text)
    content << url.toURL().text
    
    println("Set content from $url")
  } // fetchContent

  def doStuff() { 
    def content1 = new DataflowVariable()
    def content2 = new DataflowVariable()
    task { fetchContent("http://www.agiledeveloper.com", content1) }
    task { fetchContent("http://www.MacAdie.net", content2) }

    println("Waiting for data to be set")
    println("Size of content1 is ${content1.val.size()}")
    println("Size of content2 is ${content2.val.size()}")
    
  } // end doStuff



  def public static void main ( String [] args ) {  
    DataFlow001 df = new DataFlow001()
    df.doStuff()


    println('Done in main')
    // It seems to need this to print stuff to console
    // Thread.sleep(1000)
  } // end method main

} // end HollywoodActorRunner

