package info.shelfunit.util;

import java.util.List;
import java.util.ArrayList;


public class ClassUtil {

    private final Object o;
    private List< String > interfaces;
    private List< String > ancestors;
    
    private void setInterfaces() {
	interfaces = new ArrayList< String >();
	Class ci[] = o.getClass().getInterfaces();
	for ( Class nextClass : ci ) {
	    interfaces.add( nextClass.getName() );
	}
    } // setIterfaces() 

    private void setAncestors() {
	
    } // setAncestors()

    public ClassUtil( Object o2 ) {
	this.o = o2;
	this.setInterfaces();
	setAncestors();
    }
    
    public boolean doesImplement( String interfaceName ) {
	return true;
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
