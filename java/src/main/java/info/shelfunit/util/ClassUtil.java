package info.shelfunit.util;

import java.util.Set;
import java.util.LinkedHashSet;

public class ClassUtil {

    private final Object o_1;
    private Set< String > interfaces;
    private Set< String > ancestors;
    
    private void setInterfaces( Class c_1 ) {
	Class ci[] = c_1.getInterfaces();
	for ( Class nextClass : ci ) {
	    this.interfaces.add( nextClass.getName() );
	}
    } // setIterfaces() 

    private void setAncestors() {
	ancestors = new LinkedHashSet< String >();
	boolean isObject = false;
	this.setInterfaces( o_1.getClass() );
	Class parent = o_1.getClass().getSuperclass();
	
	String className = "";
	while ( !isObject  ) {
	    this.setInterfaces( parent );
	    
	    if ( parent.getName().equals( "java.lang.Object" ) ) {
		isObject = true;
	    }
	    className = parent.getName();
	    ancestors.add( className );
	    try {
		parent = Class.forName( className ).getSuperclass();
	    } catch ( Exception e ) {
		isObject = true;
	    }
	}
	
    } // setAncestors()

    public ClassUtil( Object o2 ) {
	this.o_1 = o2;
	interfaces = new LinkedHashSet< String >();
	this.setAncestors();
    }
    
    public boolean doesImplement( String interfaceName ) {
	if ( interfaces.contains( interfaceName ) ) {
	    return true;
	} else {
	    return false;
	}
    } // end implements
    
    /** This will return true if an object is an instance of a class
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
