package info.shelfunit.app.fromlinkedin;
 
// Lombok-ed version of a class posted in a LinkedIn group discussion

import java.util.UUID;
import lombok.Synchronized;
 
class LockA {
    private final Object doBLock = new Object[ 0 ];
    private final Object doALock = new Object[ 0 ];
    private String uuid = UUID.randomUUID().toString();

    void doA() {
        synchronized( doALock ) {
            System.out.println("In doA for " + uuid);
        }
    }

    void doB( LockA another ) {
        synchronized ( doBLock ) {
            System.out.println("doBLock.hashCode(): " + doBLock.hashCode());
            doA();
            another.doA();
        }
    }
 
    public static void main( String[] args ) {
        System.out.println( "starting main" );
        final LockA a1 = new LockA();
        final LockA a2 = new LockA();
        Thread second = new Thread(){
		public void run() {
		    a1.doB( a2 );
		}
	    };
        second.start();
        a2.doB( a1 );
        System.out.println( "At end of main" );
    } // end method main
} // end class