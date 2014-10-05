package info.shelfunit.app.fromlinkedin;
 
// Lombok-ed version of a class posted in a LinkedIn group discussion
 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.UUID;
import lombok.Synchronized;
 
class LockA {
    private final Object objID2 = new Object();
    private String uuid = UUID.randomUUID().toString();
     
    // synchronized void doA(){
    @Synchronized void doA() {
        System.out.println( "In doA for " + uuid );
    }
     
    // synchronized void doB( LombokA another ) {
    @Synchronized( "objID2" ) void doB( LockA another ) {
        System.out.println( "objID2.hashCode(): " + objID2.hashCode() );
        doA();
        another.doA();
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