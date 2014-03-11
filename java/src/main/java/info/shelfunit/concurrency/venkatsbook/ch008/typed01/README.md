On the Akka site, they say to use Typed actors sparingly:
http://doc.akka.io/docs/akka/2.3.0/java/typed-actors.html    
http://doc.akka.io/docs/akka/2.3.0/scala/typed-actors.html    

EnergySource.java is an interface that is implemented by EnergySourceImpl.java. They are called from UseEnergySource.java.   

I like the fact that EnergySourceImpl is just a regular POJO. It would be great if that was all you needed to make, because the instantiation is frankly kind of ugly, in both Java and Scala. Plus, the Akka site itself seems to hint that it is you should not use typed actors unless someone is threatening to kill you.      

I do not like Scala, and I do not need much encouragement to avoid using Scala APIs.    

In the book, EnergySourceImpl extends TypedActor, but according to the docs you do not need to do that anymore.  

The pages linked above link to a blog post about typed actors: http://letitcrash.com/post/19074284309/when-to-use-typedactors    

Is has this gem: This is another topic which comes up from time to time on the mailing list: typed actors are “nice”, but some things work differently or not at all, so what exactly are they good for?    

I did not read it in great detail since I forget Scala code soon after I no longer need to remember it. But since they drop so many hints about how bad typed actors are, why not just drop them?    

That said, having all the "if message instanceof XYZ" in untyped actors gets pretty cumbersome too.    

Maybe Barbie was right, and concurrency is just hard no matter how you do it.    


