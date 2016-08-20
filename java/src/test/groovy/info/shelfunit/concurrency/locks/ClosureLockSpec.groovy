package info.shelfunit.concurrency.locks

import java.sql.Timestamp

// import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Stepwise
// import spock.lang.Unroll

import org.junit.Rule
import org.junit.rules.TestName

import groovy.util.logging.Slf4j 

@Slf4j
@Stepwise
class ClosureLockSpec extends Specification {
    
    @Rule 
    TestName name = new TestName()
    
    def setup() {
        println "\n--- Starting test ${name.methodName}"
    }          // run before every feature method
    def cleanup() {}        // run after every feature method
    
    def setupSpec() {  }     // run before the first feature method
    
    def cleanupSpec() { }   // run after the last feature method

    def "test the closure lock"() {
        ClosureLock cLock = new ClosureLock()
        def x = 0
        when:
            println( "before: X is ${x}" )
            cLock.lockSomeCode( { x++ } )
        then:
            x == 1
            println( "after: X is ${x}" )
        when:
            x = 3
            println( "before: X is ${x}" )
            cLock.lockSomeCode{  
                x = x * 4
                x = x + 1
            }
        then:
            println( "after: X is ${x}" )
            x == 13
    
        when:
            x = 2
            println( "before: X is ${x}" )
            cLock.lockSomeCode( { x++; x = x + 3 } )
        then:
            println( "after: X is ${x}" ) // should be 6
            x == 6
            
        when:
            println( "\nAbout to start multiline closure\n" )
            cLock.lockSomeCode {
                println( "In multiline closure" )
                def list = [ 5, 6, 7, 8 ]
                println( "list.get(2): ${list.get( 2 )}" )
                println( "Done with multiline" )
            }
        then:
            1 == 1
    }

}

