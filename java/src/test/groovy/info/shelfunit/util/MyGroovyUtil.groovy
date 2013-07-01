package info.shelfunit.util

import org.spockframework.util.ObjectUtil

// this lets you use "equals" in tests, since GroovyUtil is an abstract class

public class MyGroovyUtil extends ObjectUtil {  
  public MyGroovyUtil() {  
    super()
  }
} // end MyGroovyUtil
