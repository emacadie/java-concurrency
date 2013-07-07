package info.shelfunit.util;

// import java.util.List;
// import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class ClassUtil {

    private final Object o;
    private Set< String > interfaces;
    private Set< String > ancestors;
    
    private void setInterfaces() {
	interfaces = new LinkedHashSet< String >();
	Class ci[] = o.getClass().getInterfaces();
	System.out.println( o.getClass().getName() + " implements " + 
	    ci.length + " interfaces" 
	);
	for ( Class nextClass : ci ) {
	    interfaces.add( nextClass.getName() );
	    System.out.println("Adding " + nextClass.getName()); 
	}
    } // setIterfaces() 

    private void setAncestors() {
	System.out.println( "Looking for ancestors of " + o.getClass().getName() );
	ancestors = new LinkedHashSet< String >();
	boolean isObject = false;
	Class parent = o.getClass().getSuperclass();
	String className = "";
	while ( !isObject  ) {
	    
	    System.out.println( "Parent is a " + parent.getName() );
	    if ( parent.getName().equals( "java.lang.Object" ) ) {
		// isObject = true;
	    }
	    className = parent.getName();
	    ancestors.add( className );
	    try {
		parent = Class.forName( className ).getSuperclass();
	    } catch (Exception e) {
		isObject = true;
	    }
	}
	
    } // setAncestors()

    public ClassUtil( Object o2 ) {
	this.o = o2;
	try {
	
	this.setInterfaces();
	this.setAncestors();
	} catch ( Exception e ) {
	    // e.printStackTrace();
	}
    }
    
    public boolean doesImplement( String interfaceName ) {
	if ( interfaces.contains( interfaceName ) ) {
	    return true;
	} else {
	    return false;
	}
    } // end implements

    
    /* This will return true if an object is an instance of a class
       specified by the argument given. The argument must be the 
       fully-qualified class name.
     */
    public boolean isInstanceOf( String fqName ) {
	if ( o.getClass().getName().equals( fqName ) ) {
	    return true;
	} else {
	    return false;
	}
    } // end instanceOf

} // end ClassUtil
