# Testing PlayFramework 1.2 and WebSockets

There are two samples on this application, one just send messages without using the other channel.
Second sample uses both channels, show a message each 1sec and if something is sent it will reply with UPPER CASED message.

	Send only:
	http://localhost:9000/status
	
	Send and Receive:
	http://localhost:9000/echo
	
	
# Notes

* Requires PlayFramework 1.2
* Once first sample never disconnects from WebSocket it locks the only thread used to development, so you must RESTART the application.