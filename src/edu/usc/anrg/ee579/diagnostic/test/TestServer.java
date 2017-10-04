package edu.usc.anrg.ee579.diagnostic.test;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.*;

//import edu.usc.anrg.ee579.diagnostic.MyServer;
//import edu.usc.anrg.ee579.diagnostic.protocol.*;
import edu.usc.anrg.ee579.diagnostic.*;


public class TestServer {
	
	static final int DEF_PORT=9777;
	
	public static void main(String[] args) throws Exception{
        int portNumber;
        
        portNumber=(args.length==1)?Integer.parseInt(args[0]):DEF_PORT;
        
        
        ServerSocket listener=new ServerSocket(portNumber);
        System.out.println("Server is listening...");
        //Socket clientSocket=listener.accept();
        
        //MyServer server=new MyServer(clientSocket);
		
        /*
         * create a MyServer object to run the server side program
         */
		try{
			while(true){
				Socket clientSocket=listener.accept();
				
		        MyServer server=new MyServer(clientSocket);
				//System.out.println("set connect...");
				server.run();
			}
		}finally{
			listener.close();
		}
        
	}
}
