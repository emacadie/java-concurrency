package info.shelfunit.app.fromroseindia;

// refactored version of http://www.roseindia.net/javatutorials/deadlocksinjava.shtml
 
import java.util.UUID;
import lombok.Synchronized;
 
public class DeadLockDemoLombok {
 
    public static String str1 = "str1";
    public static String str2 = "str2";
 
    public static void main( String[] a ) {
        Thread myThread   = new MyThreadLombok();
        Thread yourThread = new YourThreadLombok();
 
        myThread.start();
        yourThread.start();
        System.out.println( "At the end of main" );
    } // end method main
 
    private static class MyThreadLombok extends Thread {
        private final Object object1 = new Object();
 
        public void run() {
            synchronized ( object1 ) {
 
                System.out.println( "MyThread Holds lock on object object1" );
                try {
                    Thread.sleep( 10 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
 
                System.out.println( "MyThread waiting for lock on object str2" );
                synchronized ( str2 ) {
                    System.out.println( "MyThread Holds lock on objects object1, str2" );
                }
            } // synchronized ( object1 )
            System.out.println( "At the end of MyThreadLombok.run" );
        } // end method run
    } // end class MyThread
 
    private static class YourThreadLombok extends Thread {
        private final Object object2 = new Object();
 
        public void run() {
            synchronized ( object2 ) {
                System.out.println( "YourThread Holds lock on object object2" );
                try {
                    Thread.sleep( 10 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                System.out.println( "YourThread waiting for lock on object str1" );
                synchronized ( str1 ) {
                    System.out.println( "YourThread Holds lock on objects str1, object2" );
                }
            } // synchronized ( object2 )
            System.out.println( "At the end of YourThreadLombok.run" );
        } // end method run
    } // end class YourThread
 
} // end class DeadLockDemoLombok
