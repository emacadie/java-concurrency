This section deals with immutable collections inside transactions.    

Scores.java has a map and a long. It uses Scala STM. The maps has names and scores. The map is changed within a transaction. UseScores.java runs it.   

MultiverseScores.java is called by UseMultiverseScores.java. This throws an exception. I hit upon something that was never completed.

