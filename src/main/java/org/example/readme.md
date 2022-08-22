Code Walk-through: org.example
----
We have put @Transactional and hence we then enabled enableTransactionManagement and then rollback is something which is proxied.
<br/>
By default the checked exception is not supported, 
we may use @Transactional(rollback=Exception.class) to rollback on failure
<br/>

* Class marked with annotation @transactional is proxy'd

What is propagation ?
- @Transactional(propagation=Propagation.REQUIRED) [default]
- When transaction is created (Let's say for a **service** method) this may call another object maintaining a work (that is <b> repository </b>).
- If an active transaction is there happening currently, then same transaction is used for the repository. It doesn't create any other transaction.
- At the end it does commit; if failed the rollback
