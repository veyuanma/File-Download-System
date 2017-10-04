package edu.usc.anrg.ee579.diagnostic.test;

import java.io.*;

//import edu.usc.anrg.ee579.diagnostic.MyClient;
//import java.net.*;
import edu.usc.anrg.ee579.diagnostic.*;


public class TestClient {
	static final int DEF_PORT=9777;
	static final String DEF_HOST="127.0.0.1";
	
    public static void main(String[] args) throws IOException{
    	
    	String serverName;
        int serverPort;
       
        System.out.println("Usage: <hostname> <port number> OR use default server and port number");
            
        serverName=(args.length==2)?args[0]:DEF_HOST;
        serverPort=(args.length==2)?Integer.parseInt(args[1]):DEF_PORT;
        
        //serverName="127.0.0.1";
        //serverPort=9777;
        /*
         * create a MyClient object to run the client side program
         */
        MyClient client=new MyClient(serverName, serverPort);
        
        while(true){
            client.run();
        }
			
        
    }
}
