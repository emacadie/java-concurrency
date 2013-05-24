package info.shelfunit.concurrency.comparison;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.TimeUnit;

public class SingleLockHolder {

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
    // public final ReadWriteLock ssLock  = new ReentrantReadWriteLock();
    // public final ReadWriteLock fcsLock = new ReentrantReadWriteLock();
    // public final ReadWriteLock scsLock = new ReentrantReadWriteLock();

    // public final ReadWriteLock fnLock  = new ReentrantReadWriteLock();
    // public final ReadWriteLock snLock  = new ReentrantReadWriteLock();
    // public final ReadWriteLock fcnLock = new ReentrantReadWriteLock();
    // public final ReadWriteLock scnLock = new ReentrantReadWriteLock();

    public SingleLockHolder() {
	
    } // constructor

    public void setFirstString(String string1) throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstString = string1;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setSecondString(String string2) throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondString = string2;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setFirstNum(double num1) throws InterruptedException {
       	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstNum = num1;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setSecondNum(double num2) throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondNum = num2;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setFirstConcatString() throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstConcatString = firstString + secondString;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setSecondConcatString() throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondConcatString = secondString + firstString;
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setFirstCalcNum() throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    firstCalcNum = Math.pow(firstNum, secondNum); 
	} finally { fsLock.writeLock().unlock(); }
    }

    public void setSecondCalcNum() throws InterruptedException {
	try {
	    fsLock.writeLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    secondCalcNum =  Math.scalb( firstNum, Math.round( new Double(secondNum).floatValue() ) ); 
	} finally { fsLock.writeLock().unlock(); }
    }


    public String getFirstString() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstString; 
	} finally { fsLock.readLock().unlock(); }
    }

    public String getSecondString() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondString;  
	} finally { fsLock.readLock().unlock(); }
    }

    public String getFirstConcatString() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstConcatString; 
	} finally { fsLock.readLock().unlock(); }
    }

    public String getSecondConcatString() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondConcatString; 
	} finally { fsLock.readLock().unlock(); }
    }
    
    public double getFirstNum() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstNum;  
	} finally { fsLock.readLock().unlock(); }
    }

    public double getSecondNum() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondNum;  
	} finally { fsLock.readLock().unlock(); }
    }

    public double getFirstCalcNum() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return firstCalcNum; 
	} finally { fsLock.readLock().unlock(); }
    }

    public double getSecondCalcNum() throws InterruptedException { 
	try {
	    fsLock.readLock().tryLock( 100, TimeUnit.MILLISECONDS );
	    return secondCalcNum; 
	} finally { fsLock.readLock().unlock(); }
    }

} // end class package info.shelfunit.concurrency.comparison.SynchronizedHolder
// line 164
// line 148
