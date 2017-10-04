package edu.usc.anrg.ee579.diagnostic;
import java.net.*;
//import java.nio.ByteBuffer;
import java.util.HashSet;

import edu.usc.anrg.ee579.diagnostic.protocol.tcpPacket;

import java.io.*;
//import edu.usc.anrg.ee579.diagnostic.protocol.*;

public class MyServer{
	
	//static final int DEF_PORT=9777;
	//static final String DEF_HOST="localhost";
	
	//BufferedReader in;
	//PrintWriter out;
	Socket socket;
	//MyPacket msg=new MyPacket();
	
	public MyServer(Socket socket){
		this.socket=socket;
	}
	
	HashSet<String> hs=new HashSet<String>();
	
	/*
	 * handle different messages received.
	 */
	public void serverMsg(Socket socket, OutputStream out, String msg) throws IOException{
		String readyStr=new String();
		if(msg.equals("HELLO")){
		    readyStr="HELLO";
		}
	    else if(msg.equals("LIST")){
	    	readyStr="LIST, 34, Somename.txt, 67, Another.dat";
	    }
	    else if(msg.equals("1") && hs.contains("LIST")){
	    	readyStr="GET, 34, Somename.txt, A lot of names";
	    }
	    else if(msg.equals("2") && hs.contains("LIST")){
	    	readyStr="GET, 67, Another.dat, A small piece of text";
	    }
	    else if(msg.equals("END")){
	    	readyStr="SEE YOU and SERVER EXITING...";
	    	//tcpPacket(readyStr,out);
	    	//System.exit(1);
			//socket.close();
	    }
	    else{
	    	readyStr="INVALID";
	    }
		
		tcpPacket myPacket=new tcpPacket();
		myPacket.sendPacket(readyStr, out);
		//tcpPacket(readyStr,out);
	}
	
	/*
	 * convert the byte array that come from the socket to Stirng message for server to handle
	 */
	public void preprocess(byte[] bytes,OutputStream out, Socket socket) throws IOException{
		byte[] api_type=new byte[4];
	    System.arraycopy(bytes,0,api_type,0,4);
	    byte[] total_len=new byte[4];
	    System.arraycopy(bytes,4,total_len,0,4);
	    byte[] msg_len=new byte[4];
	    System.arraycopy(bytes,8,msg_len,0,4);
		byte[] msg=new byte[total_len[3]-12];
		System.arraycopy(bytes,12,msg,0,total_len[3]-12);
	    String m=new String(msg);
	    hs.add(m);
	    serverMsg(socket, out,m);
	}
	
	
	/*
	 * create child socket for communicating witht he client
	 * send message and receive message
	 */
	public void run() throws IOException{
		//PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
    	//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		OutputStream out=socket.getOutputStream();
		InputStream in=socket.getInputStream();
		
    	//BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
    	//String userInput;
    	
        //String msg;
    	byte[] msg=new byte[1000];
    	
        //while((msg=in.readLine())!=null){
    	while(in.read(msg)>0){
        	preprocess(msg,out,socket);
        	//serverMsg(socket, out, msg);
        	/*System.out.println(msg);
        	userInput=stdIn.readLine();
        	out.println(userInput);*/
        }
    	socket.close();
	}
	
	
	
	
	
 }
