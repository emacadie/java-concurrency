package info.shelfunit.concurrency.comparison;

public class SynchronizedHolder  {

    private String firstString;
    private String secondString;
    private String firstConcatString;
    private String secondConcatString;
    
    private double firstNum;
    private double secondNum;
    private double firstCalcNum;
    private double secondCalcNum;

    public SynchronizedHolder() {
	
    } // constructor

    public synchronized void setFirstString(String string1) {
	firstString = string1;
    }

    public synchronized void setSecondString(String string2) {
	secondString = string2;
    }

    public synchronized void setFirstNum(double num1) {
	firstNum = num1;
    }

    public synchronized void setSecondNum(double num2) {
	secondNum = num2;
    }

    public synchronized void setFirstConcatString() {
	firstConcatString = firstString + secondString;
    }

    public synchronized void setSecondConcatString() {
	secondConcatString = secondString + firstString;
    }

    public synchronized void setFirstCalcNum() {
	firstCalcNum = Math.pow(firstNum, secondNum); 
    }

    public synchronized void setSecondCalcNum() {
	secondCalcNum =  Math.scalb( firstNum, Math.round( new Double(secondNum).floatValue())); 
    }


    public synchronized String getFirstString() { return firstString; }
    public synchronized String getSecondString() { return secondString; }
    public synchronized String getFirstConcatString() { return firstConcatString; }
    public synchronized String getSecondConcatString() { return secondConcatString; }
    
    public synchronized double getFirstNum() { return firstNum; }
    public synchronized double getSecondNum() { return secondNum; }
    public synchronized double getFirstCalcNum() { return firstCalcNum; }
    public synchronized double getSecondCalcNum() { return secondCalcNum; }

} // end class package info.shelfunit.concurrency.comparison.SynchronizedHolder
