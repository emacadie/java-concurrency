package info.shelfunit.app.newpackage2;
 
// example from http://www.java2s.com/Code/Java/Threads/Threaddeadlock.htm
// refactored to use Lombok
 
import java.util.UUID;
import lombok.Synchronized;
 
public class DeadlockLombok  extends Object {
 
    private String objID;
    private final Object someObject = new Object();
 
    public DeadlockLombok( String id ) {
        objID = id;
    }
 
    // public synchronized void checkOther(DeadlockLombok other) {
    @Synchronized public void checkOther( DeadlockLombok other ) {
        print( "entering checkOther()" );
 
        try {
	    Thread.sleep( 2000 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        print( "invoke 'other.action()'" );
        other.action();
 
        print( "leaving checkOther()" );
    }
 
    // @Synchronized public void action() { // does not work
    @Synchronized( "someObject" ) public void action() { // works
	// public synchronized void action() {
        print( "entering action()" );
 
        // simulate some work here
        try {
            Thread.sleep( 500 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        print( "leaving action()" );
    }
 
    public void print( String msg ) {
        threadPrint( "objID=" + objID + " - " + msg );
    }
 
    public static void threadPrint( String msg ) {
        String threadName = Thread.currentThread().getName();
        System.out.println( threadName + ": " + msg );
    }
 
    public static void main( String[] args ) {
        System.out.println( "Starting DeadlockLombok" );
        final DeadlockLombok obj1 = new DeadlockLombok( "Thread 1" );
        final DeadlockLombok obj2 = new DeadlockLombok( "Thread 2" );
 
        Runnable runA = new Runnable() {
	    public void run() {
	        obj1.checkOther( obj2 );
	    }
	};
 
        Thread thread = new Thread( runA, "A" );
        thread.start();
 
        try {
            Thread.sleep( 200 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        Runnable runB = new Runnable() {
	    public void run() {
	        obj2.checkOther( obj1 );
	    }
	};
 
        Thread threadB = new Thread( runB, "B" );
        threadB.start();
 
        try {
            Thread.sleep( 5000 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        threadPrint( "finished sleeping" );
 
        threadPrint( "about to interrupt() threadA" );
        thread.interrupt();
 
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        threadPrint( "about to interrupt() threadB" );
        threadB.interrupt();
 
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException x ) {
            x.printStackTrace();
        }
 
        threadPrint( "did that break the deadlock?" );
        System.out.println( "End method main" );
    } // end method main
 
} // end class