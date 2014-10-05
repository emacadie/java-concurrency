package info.shelfunit.app.really.big.tutorial;
 
// Lombok-ed refactoring of deadlock example from Really Big Tutorial:
// http://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
 
import java.util.UUID;
import lombok.Synchronized;
 
public class DeadlockLombok {
 
    static class Friend {
        private final Object readLock = new Object();
        private final Object readLock2 = new Object();
        private final String name;
        public Friend( String name ) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
 
        @Synchronized( "readLock" ) public  void bow( Friend bower ) {
            System.out.format( "%s: %s has bowed to me!%n",
			       this.name, bower.getName() );
            bower.bowBack( this );
        }
 
        // either one works
        // @Synchronized( "readLock2" ) public void bowBack( Friend bower ) {
        @Synchronized public void bowBack( Friend bower ) {
            System.out.format("%s: %s has bowed back to me!%n",
			      this.name, bower.getName() );
        }
    } // end class Friend

    public static void main( String[] args ) {
        final Friend alphonse = new Friend( "Alphonse" );
        final Friend gaston = new Friend( "Gaston" );
        new Thread(new Runnable() {
		public void run() { alphonse.bow( gaston ); }
	    }).start();
        new Thread(new Runnable() {
		public void run() { gaston.bow( alphonse ); }
	    }).start();
 
        System.out.println( "At the end of main" );
    } // end method main
 
} // end class DeadlockLombok
