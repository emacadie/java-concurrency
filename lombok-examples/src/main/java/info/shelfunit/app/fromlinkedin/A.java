package info.shelfunit.app.fromlinkedin;
 
// posted on a LinkedIn group discussion
 
import java.util.UUID;
 
 
class A {
 
    private String uuid = UUID.randomUUID().toString();
     
    synchronized void doA() {
        System.out.println( "In doA for " + uuid );
    }
     
    synchronized void doB( A another ) {
        doA();
        another.doA();
    }
 
    public static void main( String[] args ) {
        System.out.println( "starting main" );
        final A a1 = new A();
        final A a2 = new A();
        Thread second = new Thread() {
		public void run() {
		    a1.doB( a2 );
		}
	    };
        second.start();
        a2.doB( a1 );
        System.out.println( "At end of main" );
    } // end method main
} // end class
