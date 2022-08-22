## Programmatic vs. Declarative

Spring supports two types of transaction management −
* Programmatic transaction management − This means that you have to manage the transaction with the help of programming. That gives you extreme flexibility, but it is difficult to maintain.
* Declarative transaction management − This means you separate transaction management from the business code. You only use annotations or XML-based configuration to manage the transactions.

Declarative transaction management is preferable over programmatic transaction management though it is less flexible than programmatic transaction management, which allows you to control transactions through your code. But as a kind of crosscutting concern, declarative transaction management can be modularized with the AOP approach. Spring supports declarative transaction management through the Spring AOP framework.

