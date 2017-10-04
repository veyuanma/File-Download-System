# File Upload System

This is a server-client file upload system using socket programming in Java.

How to start:

	Setup server:
	1. go to src/edu/usc/anrg/ee579/diagnostic directory:
		javac protocol/tcpPacket.java MyServer.java test/TestServer.java

	2. go to src directory:
		java -classpath . edu.usc.anrg.ee579.diagnostic.test.TestServer

	Setup client:
	1. go to src/edu/usc/anrg/ee579/diagnostic directory:
		javac protocol/tcpPacket.java MyClient.java test/TestClient.java

	2. go to src directory:
		java -classpath . edu.usc.anrg.ee579.diagnostic.test.TestClient


# Q & A

1. How would I be able to use another customised class in my class？

I fount this stackoverflow answer is really neat and clear, feel free to check it out:
	https://stackoverflow.com/questions/13657787/import-custom-java-class

Also, learn more on: http://docs.oracle.com/javase/tutorial/java/package/index.html

2. If you encounter the error: "Error: Could not find or load main class":

Please check out https://stackoverflow.com/questions/7485670/error-could-not-find-or-load-main-class