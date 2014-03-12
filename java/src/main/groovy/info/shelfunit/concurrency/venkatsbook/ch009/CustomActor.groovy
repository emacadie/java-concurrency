package info.shelfunit.concurrency.venkatsbook.ch009

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

// from Programming Concurrency on the JVM by Venkat Subramaniam

class CustomActor extends DefaultActor {
    // @Override protected void act() {
    @Override protected void act() {
        loop {
            react {
                println it
            }
        }
    }
}

