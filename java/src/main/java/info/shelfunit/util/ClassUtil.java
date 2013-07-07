package info.shelfunit.util;

// import java.util.List;
// import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class ClassUtil {

    private final Object o_1;
    private Set< String > interfaces;
    private Set< String > ancestors;
    
    private void setInterfaces( Class c_1 ) {
	
	Class ci[] = c_1.getInterfaces();
	System.out.println( c_1.getName() + " implements " + 
	    ci.length + " interfaces" 
	);
	for ( Class nextClass : ci ) {
	    this.interfaces.add( nextClass.getName() );
	    System.out.println("Adding " + nextClass.getName()); 
	}
    } // setIterfaces() 

    private void setAncestors() {
	System.out.println( "Looking for ancestors of " + o_1.getClass().getName() );
	ancestors = new LinkedHashSet< String >();
	boolean isObject = false;
	this.setInterfaces( o_1.getClass() );
	Class parent = o_1.getClass().getSuperclass();
	
	String className = "";
	while ( !isObject  ) {
	    this.setInterfaces( parent );
	    if (parent.getName() != null) {
		System.out.println( parent.getName() + " is not null"  );
	    }
	    
	    System.out.println( "Parent is a " + parent.getName() );
	    if ( parent.getName().equals( "java.lang.Object" ) ) {
		isObject = true;
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
	this.o_1 = o2;
	// try {
	interfaces = new LinkedHashSet< String >();
	// this.setInterfaces();
	this.setAncestors();
	// } catch ( Exception e ) {
	    // e.printStackTrace();
	    // }
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
	if ( o_1.getClass().getName().equals( fqName ) ) {
	    return true;
	} else {
	    return false;
	}
    } // end instanceOf

} // end ClassUtil
