package info.shelfunit.app.really.big.tutorial;

import lombok.Synchronized;

public class DeadlockSync {
    static class Friend {
        private final Object bowLock  = new Object[ 0 ];
        private final Object bowBackLock = new Object[ 0 ];
        private final String name;
        public Friend( String name ) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }

        public  void bow( DeadlockSync.Friend bower ) {
            synchronized ( bowLock ) {
                System.out.format( "%s: %s has bowed to me!%n",
                        this.name, bower.getName() );
                bower.bowBack(this);
            }
        }

        // either one works
        // @Synchronized( "readLock2" ) public void bowBack( Friend bower ) {
        public void bowBack( DeadlockSync.Friend bower ) {
            synchronized( bowBackLock ) {
                System.out.format( "%s: %s has bowed back to me!%n",
                        this.name, bower.getName() );
            }
        }
    } // end class Friend

    public static void main( String[] args ) {
        final DeadlockLombok.Friend alphonse = new DeadlockLombok.Friend( "Alphonse" );
        final DeadlockLombok.Friend gaston = new DeadlockLombok.Friend( "Gaston" );
        new Thread( new Runnable() {
            public void run() { alphonse.bow( gaston ); }
        } ).start();
        new Thread( new Runnable() {
            public void run() { gaston.bow( alphonse ); }
        } ).start();

        System.out.println( "At the end of main" );
    } // end method main

}
