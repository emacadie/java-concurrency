package info.shelfunit.concurrency.comparison;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.TimeUnit;

public class LockHolder {

    private String firstString;
    private String secondString;
    private String firstConcatString;
    private String secondConcatString;
    
    private double firstNum;
    private double secondNum;
    private double firstCalcNum;
    private double secondCalcNum;

    // I assume I need a lock for every field
    public final ReadWriteLock fsLock  = new ReentrantReadWriteLock();
    public final ReadWriteLock ssLock  = new ReentrantReadWriteLock();
    public final ReadWriteLock fcsLock = new ReentrantReadWriteLock();
    public final ReadWriteLock scsLock = new ReentrantReadWriteLock();

    public final ReadWriteLock fnLock  = new ReentrantReadWriteLock();
    public final ReadWriteLock snLock  = new ReentrantReadWriteLock();
    public final ReadWriteLock fcnLock = new ReentrantReadWriteLock();
    public final ReadWriteLock scnLock = new ReentrantReadWriteLock();

    public LockHolder() {
	
    } // constructor

    public void setFirstString(String string1) throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstString = string1;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setSecondString(String string2) throws InterruptedException {
	try {
	    ssLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondString = string2;
	} finally { ssLock.writeLock().unlock(); }
    }

    public void setFirstNum(double num1) throws InterruptedException {
       	try {
	    fnLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstNum = num1;
	} finally { fnLock.writeLock().unlock(); }
    }

    public void setSecondNum(double num2) throws InterruptedException {
	try {
	    snLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondNum = num2;
	} finally { snLock.writeLock().unlock(); }
    }

    public void setFirstConcatString() throws InterruptedException {
	try {
	    fcsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstConcatString = firstString + secondString;
	} finally { fcsLock.writeLock().unlock(); }
    }

    public void setSecondConcatString() throws InterruptedException {
	try {
	    scsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondConcatString = secondString + firstString;
	} finally { scsLock.writeLock().unlock(); }
    }

    public void setFirstCalcNum() throws InterruptedException {
	try {
	    fcnLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstCalcNum = Math.pow(firstNum, secondNum); 
	} finally { fcnLock.writeLock().unlock(); }
    }

    public void setSecondCalcNum() throws InterruptedException {
	try {
	    scnLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondCalcNum =  Math.scalb( firstNum, Math.round( new Double(secondNum).floatValue() ) ); 
	} finally { scnLock.writeLock().unlock(); }
    }


    public String getFirstString() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstString; 
	} finally { fsLock.readLock().unlock(); }
    }

    public String getSecondString() throws InterruptedException { 
	try {
	    ssLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondString;  
	} finally { ssLock.readLock().unlock(); }
    }

    public String getFirstConcatString() throws InterruptedException { 
	try {
	    fcsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstConcatString; 
	} finally { fcsLock.readLock().unlock(); }
    }

    public String getSecondConcatString() throws InterruptedException { 
	try {
	    scsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondConcatString; 
	} finally { scsLock.readLock().unlock(); }
    }
    
    public double getFirstNum() throws InterruptedException { 
	try {
	    fnLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstNum;  
	} finally { fnLock.readLock().unlock(); }
    }

    public double getSecondNum() throws InterruptedException { 
	try {
	    snLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondNum;  
	} finally { snLock.readLock().unlock(); }
    }

    public double getFirstCalcNum() throws InterruptedException { 
	try {
	    fcnLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstCalcNum; 
	} finally { fcnLock.readLock().unlock(); }
    }

    public double getSecondCalcNum() throws InterruptedException { 
	try {
	    scnLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondCalcNum; 
	} finally { scnLock.readLock().unlock(); }
    }

} // end class package info.shelfunit.concurrency.comparison.SynchronizedHolder
// line 164
// line 148
