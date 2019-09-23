This projects helps to build and deploy a microservice by applying 
DDD, CQRS, Event Souring principles on AXON JVM stack.

 
<h2>What you'll build</h2>
You'll build a sample microservice with one bounded context.

<h2> What you'll need </h2>
AXON stack - framework and server <br/> 
Java <br/>
SpringBoot <br/> 
Postgres <br/>

Learn more about AXON stack<br/>
AXON is a open source stack to develop java based microservices . https://axoniq.io


<h2>package structure</h2>
This stack requires you to architect/structure your code is a particular way.


<h3>api</h3>
This is where the core APIs live. 
APIs are exposed as command and queries which a client can use to interact 
with the system. 
This package also contains the Domain Events leveraged by commands and queries 
   

<h3>command</h3>
This package contains model which serves the command side of application. 
These model serves the commands only.  


<h3>query</h3>
This package contains model which serves the query side of application. 
These model serves the queries only.  

<h3>client</h3>
This package contains a domain service for test purposes.

----
set up
----
For this DEMO, I am using the Event store which comes as part of AXON stack.

1. spin up the docker container instance of AXON server : 
docker run -it --rm --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver

Check AXON dasboard : http://localhost:8024/

2. spin up the docker container instance of postgres :
docker run -it --rm --name postgres  -p 5432:5432 -e POSTGRES_USER=planmatch -e POSTGRES_PASSWORD=secret postgres:9.6

----
run application
----
Run this springboot app from PlanMatchService.

----
verify
----

----
Summary
----
Congratulations on spinning up your microservices with AXON stack. 
