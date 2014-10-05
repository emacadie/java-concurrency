package info.shelfunit.app.fromroseindia;

// from http://www.roseindia.net/javatutorials/deadlocksinjava.shtml
 
public class DeadLockDemo extends Thread {
    public static String str1 = "str1";
    public static String str2 = "str2";
 
    public static void main( String[] a ) {
        Thread myThread = new MyThread();
        Thread yourThread = new YourThread();
 
        myThread.start();
        yourThread.start();
        System.out.println( "At the end of main" );
    } // end method main
 
    private static class MyThread extends Thread {
        public void run() {
            synchronized ( str1 ) {
                System.out.println( "MyThread Holds lock on object str1" );
                try {
                    Thread.sleep( 10 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
 
                System.out.println( "MyThread waiting for lock on object str2" );
                synchronized ( str2 ) {
                    System.out.println( "MyThread Holds lock on objects str1, str2" );
                }
            }
            System.out.println( "At the end of MyThread.run" );
        } // end method run
    } // end class MyThread
 
    private static class YourThread extends Thread {
        public void run() {
            synchronized ( str2 ) {
                System.out.println( "YourThread Holds lock on object str2" );
                try {
		    Thread.sleep( 10 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                System.out.println( "YourThread waiting for lock on object str1" );
                synchronized ( str1 ) {
                    System.out.println( "YourThread Holds lock on objects str1, str2" );
                }
            }
            System.out.println( "At the end of YourThread.run" );
        } // end method run
    } // end class YourThread
 
} // end class DeadLockDemo
