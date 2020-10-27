package info.shelfunit.app.newpackage2;

import lombok.Synchronized;

import java.util.UUID;

public class DeadlockSync {
    private String objID;
    private final Object actionObject = new Object[ 0 ];
    private final Object checkOtherObject = new Object[ 0 ];

    public DeadlockSync( String id ) {
        objID = id;
    }

    // public synchronized void checkOther(DeadlockLombok other) {

    public void checkOther( DeadlockSync other ) {
        synchronized( checkOtherObject ) {
            print("entering checkOther()");

            try {
                Thread.sleep( 2000 );
            } catch ( InterruptedException x ) {
                x.printStackTrace();
            }

            print( "invoke 'other.action()'" );
            other.action();

            print( "leaving checkOther()" );
        }
    }

    // @Synchronized public void action() { // does not work
    // @Synchronized( "someObject" )
    public void action() { // works
        synchronized( actionObject ) {
            print( "entering action()" );

            // simulate some work here
            try {
                Thread.sleep( 500 );
            } catch ( InterruptedException x ) {
                x.printStackTrace();
            }

            print( "leaving action()" );
        }
    }

    public void print( String msg ) {
        threadPrint( "objID=" + objID + " - " + msg );
    }

    public static void threadPrint( String msg ) {
        String threadName = Thread.currentThread().getName();
        System.out.println( threadName + ": " + msg );
    }

    public static void main( String[] args ) {
        System.out.println( "Starting DeadlockSync" );
        final DeadlockSync obj1 = new DeadlockSync( "Thread 1" );
        final DeadlockSync obj2 = new DeadlockSync( "Thread 2" );

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

}
