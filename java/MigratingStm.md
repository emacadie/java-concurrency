import akka.stm.Ref;  
import akka.stm.Atomic;  
becomes   
import scala.concurrent.stm.Ref;  
import scala.concurrent.stm.Ref.View;    
import scala.concurrent.stm.japi.STM;   

final private Ref<Integer> balance = new Ref<Integer>();   
becomes  
final View< Integer > balance = STM.newRef( new Integer(0) );  

public Account(int initialBalance) { balance.swap(initialBalance); }  
becomes   
public Account(int initialBalance) { balance.swap(initialBalance); }   
In ScalaSTM, I think you can use set and swap interchangably for the most part. The only difference is swap() will return the old value.   


public int getBalance() { return balance.get(); }   
is also the same.   

A transaction:   
public void transfer(
    final Account from, final Account to, final int amount) {
        new Atomic<Boolean>() {
            public Boolean atomically() {
                to.deposit(amount);
                from.withdraw(amount);
                return true;
            }
        }.execute();
    }
);
Becomes  
public void transfer(final Account from, final Account to, final int amount) {
     STM.atomic( new Runnable() {
         public void run() {
	     to.deposit(amount);
      	     from.withdraw( amount);
          } // end run
    });
} // end transfer

In Multiverse:   
How to set:   
new Runnable() { public void run() { balance.set(initialBalance); } };    
how to get:  
You can just call atomicGet  
If you are in StmUtils.atomic(), you can use plain old get   
But sometimes you have to do a bit more: 
B