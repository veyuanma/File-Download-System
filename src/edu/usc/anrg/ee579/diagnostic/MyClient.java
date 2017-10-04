package edu.usc.anrg.ee579.diagnostic;
import java.io.*;
import java.net.*;
//import java.nio.ByteBuffer;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import edu.usc.anrg.ee579.diagnostic.protocol.*;

public class MyClient{
	
	
	//BufferedReader in;
	//PrintWriter out;
	//Socket socket;
	//MyPacket msg=new MyPacket();
	String serverName;
	int serverPort;
	
	public MyClient(String serverName, int serverPort){
		this.serverName=serverName;
		this.serverPort=serverPort;
	}
	/*
	 * indicate all the options that the client can choose
	 */
	public void clientOptions(){
		System.out.println("You can send the following message:");
		System.out.println("HELLO");
		System.out.println("LIST");
		System.out.println("GET (specifiedby its index)");
		System.out.println("END");
		System.out.println("Please choose one...");
	}
	
	
	/*
	 * convert the byte array from the socket to String type for client to display on the screen
	 */
	public String preprocess(byte[] bytes) throws IOException{
		byte[] api_type=new byte[4];
	    System.arraycopy(bytes,0,api_type,0,4);
	    byte[] total_len=new byte[4];
	    System.arraycopy(bytes,4,total_len,0,4);
	    byte[] msg_len=new byte[4];
	    System.arraycopy(bytes,8,msg_len,0,4);
		byte[] msg=new byte[total_len[3]-12];
		System.arraycopy(bytes,12,msg,0,total_len[3]-12);
	    String m=new String(msg);
	    return m;
	}
	
	/*
	 * create socket to communicate with the server
	 * send message and receive message
	 * display the message to the screen in proper way
	 */
	public void run() throws IOException{
		
    	Socket mySocket=new Socket(serverName, serverPort);
    	//PrintWriter out=new PrintWriter(mySocket.getOutputStream(),true);
    	OutputStream out=mySocket.getOutputStream();
    	InputStream in=mySocket.getInputStream();
    	
    	//BufferedReader in=new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
    	BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
    	
		clientOptions();
		
    	String userInput;
    	while((userInput=stdIn.readLine())!=null){
    		
    		if(userInput.equals("END")){
    			mySocket.close();
    			System.out.println("Connection terminated");
    			System.exit(1);
    		}
    		//tcpPacket(userInput,out);
    		
    		tcpPacket myPakcet=new tcpPacket();
    		myPakcet.sendPacket(userInput, out);
    		
    		//out.println(userInput);
    		byte[] msg=new byte[1000];
    		String response=new String();
    		if(in.read(msg)>0){
    			response=preprocess(msg);
    		}
    		//String response=in.readLine();
    		
    		//System.out.println(response);
    		
    		char first=response.charAt(0);
    		if(first=='L'){
    			System.out.println("1. Somename.txt");
    			System.out.println("2. Another.dat");
    			System.out.println("You can get the file by pressing 1 or 2 on the keyboard");
    		}
    		else if(response=="HELLO"){
    			System.out.println(response);
				clientOptions();
    		}
    		/*else if(response=="SEE YOU and SERVER EXITING..."){
    			System.out.println(response);
    			mySocket.close();
    		}*/
    		else{
    			System.out.println(response);
    		}
    	}
    	//mySocket.close();
	}
	
	
	
    
}
