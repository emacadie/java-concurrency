Account.java uses Scala STM. It has a balance, as well as methods for deposit and withdrawals. AccountService.java instantiates a couple of Account objects, and tries to do a few transfers. The method AccountService.transfer does its work within a call to STM.atomic. It includes calls to Account.deposit and Account.withdraw, each of which do their work within a call to STM.atomic. So we have nested transactions.    

MultiverseAccount.java and MultiverseAccountService.java are pretty much the same, except they use Multiverse instead of Scala STM.   

The line number count is pretty close.   

AccountOperationFailedException.java is just an exception.   




    

