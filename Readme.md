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
Run this as springboot app

----
verify
----
Check the console log to see your commands and queries in action. 

2019-09-23 13:09:00.013 DEBUG 5200 --- [main] com.example.match.client.DomainService   : issuing create command
2019-09-23 13:09:00.201  INFO 5200 --- [.match.query]-0] o.a.e.TrackingEventProcessor             : Using current Thread for last segment worker: TrackingSegmentWorker{processor=com.example.match.query, segment=Segment[0/0]}
2019-09-23 13:09:00.213  INFO 5200 --- [.match.query]-0] o.a.e.TrackingEventProcessor             : Fetched token: null for segment: Segment[0/0]
2019-09-23 13:09:00.225  INFO 5200 --- [.match.query]-0] o.a.a.c.event.axon.AxonServerEventStore  : open stream: 0
Security framework of XStream not initialized, XStream is probably vulnerable.
2019-09-23 13:09:00.350 DEBUG 5200 --- [mmandReceiver-0] com.example.match.command.Inquiry        : handling cmd InquiryCreateCommand(id=d6a323cc-fd78-425a-9825-a3032c6998b8, firstName=simar, status=create)
2019-09-23 13:09:00.355 DEBUG 5200 --- [mmandReceiver-0] com.example.match.command.Inquiry        : applying evt InquiryCreatedEvent(id=d6a323cc-fd78-425a-9825-a3032c6998b8, firstName=simar, status=create)
2019-09-23 13:09:00.545 DEBUG 5200 --- [.match.query]-0] c.e.match.query.InquirySummaryProjector  : projecting evt InquiryCreatedEvent(id=d6a323cc-fd78-425a-9825-a3032c6998b8, firstName=simar, status=create)
2019-09-23 13:09:00.570 DEBUG 5200 --- [main] com.example.match.client.DomainService   : issuing score command
2019-09-23 13:09:00.619 DEBUG 5200 --- [mmandReceiver-1] com.example.match.command.Inquiry        : applying evt InquiryCreatedEvent(id=d6a323cc-fd78-425a-9825-a3032c6998b8, firstName=simar, status=create)
2019-09-23 13:09:00.622 DEBUG 5200 --- [mmandReceiver-1] com.example.match.command.Inquiry        : handling cmd InquiryScoreCommand(id=d6a323cc-fd78-425a-9825-a3032c6998b8, score=5, status=complete)
2019-09-23 13:09:00.623 DEBUG 5200 --- [mmandReceiver-1] com.example.match.command.Inquiry        : applying evt InquiryScoredEvent(id=d6a323cc-fd78-425a-9825-a3032c6998b8, score=5, status=complete)
2019-09-23 13:09:00.644 DEBUG 5200 --- [.match.query]-0] c.e.match.query.InquirySummaryProjector  : projecting evt InquiryScoredEvent(id=d6a323cc-fd78-425a-9825-a3032c6998b8, score=5, status=complete)
2019-09-23 13:09:00.660 DEBUG 5200 --- [main] com.example.match.client.DomainService   : querying inquiry
2019-09-23 13:09:00.744 DEBUG 5200 --- [main] com.example.match.client.DomainService   : inquiry queried InquirySummaryView(id=d6a323cc-fd78-425a-9825-a3032c6998b8, firstName=simar, initialState=create, currentState=complete, score=5)

                  yuhu 

Some other tests will help gain better understanding.
Try to issue command in following different scenarios.
1. firstName is 'invalid'
2. score is 0 or less
  
----
Summary
----
Congratulations on spinning up your microservices with AXON stack.

More information can be found here: 
https://medium.com/@bawa.simar/building-microservices-by-applying-ddd-cqrs-and-event-sourcing-on-axon-java-sprint-boot-platform-2e12289b8a2b
