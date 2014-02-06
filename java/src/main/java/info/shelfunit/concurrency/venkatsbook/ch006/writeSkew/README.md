This deals with write-skew anomoly.   

From http://en.wikipedia.org/wiki/Snapshot_isolation:  In a write skew anomaly, two transactions (T1 and T2) concurrently read an overlapping data set (e.g. values V1 and V2), concurrently make disjoint updates (e.g. T1 updates V1, T2 updates V2), and finally concurrently commit, neither having seen the update performed by the other. Were the system serializable, such an anomaly would be impossible, as either T1 or T2 would have to occur "first", and be visible to the other. In contrast, snapshot isolation permits write skew anomalies.    

In Portfolio.java, we have two accounts, and their total balance must never go below $1000. UsePortfolio.java tries to take out enough to get below that by making a couple of withdrawals. The constraint is not violated. However, if you run it a few times, you do not always get the same results. Sometimes the balances are $500 and $500, sometimes they are $400 and $600. I used ScalaSTM.    

In his version he makes a TransactionBuilder, but it looks like I did not have to do that.   


