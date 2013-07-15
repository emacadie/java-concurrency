package info.shelfunit.concurrency.venkatsbook.ch009

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

// from Programming Concurrency on the JVM by Venkat Subramaniam

class HollywoodActor extends DefaultActor { 

  def name

  public HollywoodActor( actorName ) { 
    println "In the constructor"
    name = actorName
    println "set name to $name"
  }

  @Override
  void act() {
    loop { 
    
      react { role ->
	println " "
	println "$name playing the role $role"
	println "$name runs in ${Thread.currentThread()}"
      }
     
    }
  } // end act

} // end HollywoodActor

