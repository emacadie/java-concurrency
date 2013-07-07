package info.shelfunit.util

import spock.lang.Specification

import java.util.List;
import java.util.ArrayList;

public class ClassUtilTest extends Specification { 

  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def "test a few classes for instanceOf"() { 
    String s = "this is a string"
    ClassUtil cuString = new ClassUtil(s)
    expect:
    mga.that(cuString.isInstanceOf("java.lang.String"))
    mga.that(!cuString.isInstanceOf("String"))
    mga.that(!cuString.isInstanceOf("java.util.ArrayList"))

  }

  def "test for implements"() { 
    List< String > sList = new ArrayList< String >()
    ClassUtil sListCU = new ClassUtil(sList)


    expect:
    mga.that(sListCU.isInstanceOf("java.util.ArrayList"))
    mga.that(sListCU.doesImplement("java.io.Serializable"))
    mga.that(sListCU.doesImplement("java.lang.Cloneable"))
    mga.that(sListCU.doesImplement("java.util.Collection"))
    mga.that(sListCU.doesImplement("java.util.List"))
    mga.that(sListCU.doesImplement("java.util.RandomAccess"))
    mga.that(!sListCU.doesImplement("RandomAccess"))
    mga.that(!sListCU.doesImplement("java.applet.AppletContext"))
  }

} // end class ClassUtilTest 

