package info.shelfunit.testing;

import akka.actor.UntypedActor;

public class MyActor extends UntypedActor {

    public void onReceive(Object o) throws Exception {
	if (o.equals("say42")) {
	    System.out.println( "Got the 42 message" );
	    getSender().tell(42, getSelf());
	} else if (o instanceof Exception) {
	    System.out.println( "Did not get the 42 message" );
	    throw (Exception) o;
	}
    }
    public boolean testMe() { 
	return true; 
	// return false;
    }

} // end MyActor

