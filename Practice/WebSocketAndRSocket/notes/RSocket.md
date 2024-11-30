Works with TCPIP and Websocket
Bi-directional
Multiple interaction. 
- Request and Response
- Fire and Forgot
- Request and Response of a stream 

Java 11

RSocket Client (For Testing)

In application.properties
spring.rsocket.server.port=7000
May do lazy initialization
------------------------------------------------------------
#### Request and Response (Mostly used for HTTP)


Easy way to do quick testing
rsc --debug --request --data {"message";""} --route request-response --stacktrace tcpip:url



@SpringBootTest


Stream Out Stream In
-------------------



Messaging exchan ge:
Single In Single out: Request Response
MultipleIn/SingleIn and MultipleBack: Streaming
Multiple Value concurrent with multiple value receive back: Channel
Single Value in but no response: Fire and Forgot

