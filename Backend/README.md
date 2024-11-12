Websocket
Spring Web

In Web socket once connected we dont need to send header. It helps to reduce size.



 Websocket:
 Client   -> Request -> Server
 Server -> Hand Shake -> Client
 Client <-> Websocket <-> Server 
 

HTTP Connection
Client  -> Request -> Server
Server -> Response -> Client
Connection Terminated


Handshake:
Client and server establish connection.
Client send request with header: Request to upgrade connection from HTTP to websocket.
Request include websocket key: Base64 randomly generated value.
If supported, return 101.
Header with hash of websocket key. Client validate the connection.
Less overhead once connected


ws
wss-> Secure