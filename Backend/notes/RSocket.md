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

rsc --debug --fnf --data {"message";""} --route request-response --stacktrace tcpip:url
rsc --debug --stream --data {"message";""} --route request-response --stacktrace tcpip:url




