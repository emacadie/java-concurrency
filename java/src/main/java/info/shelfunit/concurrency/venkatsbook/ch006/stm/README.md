EnergySource.java uses Scala STM. See [the website here](http://nbronson.github.io/scala-stm/) and see [the Scaladoc here](http://nbronson.github.io/scala-stm/api/0.7/index.html#package). Before Scala used [Multiverse for STM](http://multiverse.codehaus.org/overview.html). Right now Scala STM is not being updated because the developers feel it does enough as it is. Multiverse seems dead, although for the most part it works pretty well. The developers of GPars have stated they might absorb it.      

EnergySource.java is pretty close to his original. Like his original, it is called by UseEnergySource.java. EnergySource.useEnergy has the STM. It has a call to scala.concurrent.stm.japi.STM.atomic, which has an inline Callable. It does not look too clean to me. EnergySource.replenish also have a call to STM.atomic, with an inline Runnable. The whole thing is pretty hairy. At some point I may go through it in more detail.        

UseSeparateEnergySource.java calls SeparateEnergySource.java. The calls to STM.atomic do not call inline Runnables or Callables. Instead, they make calls to CallableUseEnergy.java and ReplenishRunnable.java. The results are the same.     

Likewise, UseMultiverseEnergySource.java calls MultiverseEnergySource.java, which calls MultiverseReplenish.java and MultiverseUseEnergy.java. I think that MultiverseUseEnergy.java should probably be given another name. So should CallableUseEnergy. Perhaps MultiverseEnergyUser and CallableEnergyUser.  







