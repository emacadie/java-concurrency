package info.shelfunit.util;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <p>
 * Yet another wrapper class for Log4J. It looks for log4j.properties in the 
 * root of the class path. For Tomcat apps, it <b>must</b> be log4j.properties 
 * with a lower-case J. It is a singleton, so each class that logs will have to call
 * the class, and then call getLogger() to use the org.apache.log4j.Logger
 * object.
 * </p>
 * <p>Using DEBUG in Tomcat apps creates very large log files. Once I got a 
 * 10 MB file just by starting Tomcat and displaying the index.jsp page. So you
 * might want to use INFO for your DEBUG and WARN for your INFO. Sort of like
 * &quot;40 is the new 30,&quot; &quot;WARN is the new INFO.&quot;</p>
 * @author ericm
 */

public class ShelfLogger {
    
    private ShelfLogger() {
        try {
            Integer theInt = new Integer( this.hashCode() );
            logger = Logger.getLogger( theInt.toString() );
            URL theURL = getClass().getResource( "/log4j.properties" );
            PropertyConfigurator.configure( theURL );
        } catch ( Exception ex ) {
            System.out.println( "Issue getting log4j config" );
            ex.printStackTrace( System.out );
        }
    } // end constructor
    
    private static Logger logger; 

    /**
     * <p>After using getInstance() to get the instance of the class, use this
     * to call the org.apache.log4j.Logger object.</p>
     * <pre>
     * Logger logger = shelfLogger.getLogger();
     * </pre>
     * @return Logger Logger Breaker 99. 10-4, good buddy.
     */
    public Logger getLogger() {
        return logger;
    }

    /*
    public void setLogger( Logger logger ) {
        this.logger = logger;
    }
    */
    private static class ShelfLoggerLazyHolder {
         private static final ShelfLogger something = new ShelfLogger();
    }
    
    /** 
     * <p>Use this method to access the class. This is a singleton.</p>
     * Call it like this: 
     * <pre>this.shelfLogger = ShelfLogger.getInstance();</pre>
     * @return ShelfLogger
     */
    public synchronized static ShelfLogger getInstance() {
         return ShelfLoggerLazyHolder.something;
    }
	
} // end class ShelfLogger 
