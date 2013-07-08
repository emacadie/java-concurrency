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
	
	boolean isObject = false;
	this.setInterfaces( o_1.getClass() );
	Class parent = o_1.getClass().getSuperclass();
	String className = "";
	while ( !isObject  ) {
	    this.setInterfaces( parent );
	    className = parent.getName();
	    if ( className.equals( "java.lang.Object" ) ) {
		isObject = true;
	    }
	    ancestors.add( className );
	    try {
		parent = Class.forName( className ).getSuperclass();
	    } catch ( Exception e ) {
		isObject = true;
	    }
	} // while ( !isObject  ) {
	
    } // setAncestors()

    public ClassUtil( Object o2 ) {
	this.o_1 = o2;
	interfaces = new LinkedHashSet< String >();
	ancestors  = new LinkedHashSet< String >();
	this.setAncestors();
    }

    /** This will return true if the object sent to the constructor 
       implements the interface specified by the argument given. The argument must be the fully-qualified interface name.
     */
    public boolean doesImplement( String interfaceName ) {
	if ( interfaces.contains( interfaceName ) ) {
	    return true;
	} else {
	    return false;
	}
    } // end doesImplement
    
    /** This will return true if the object sent to the constructor is an instance of a class
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
