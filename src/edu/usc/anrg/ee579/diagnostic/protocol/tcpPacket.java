package edu.usc.anrg.ee579.diagnostic.protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class tcpPacket {
String msg;
public tcpPacket(){
	;
}
/*
 * convert the String message to byte type and send it out to the output stream 
 */
public void sendPacket(String msg, OutputStream out) throws IOException  {
	byte[] msgBytes=msg.getBytes();
    int msgLen=msgBytes.length;
    byte[] api_type = ByteBuffer.allocate(4).putInt(12).array();
    byte[] total_len = ByteBuffer.allocate(4).putInt(12+msgLen).array();
    byte[] msg_len = ByteBuffer.allocate(4).putInt(msgLen).array();
    
    byte[] packet=new byte[12+msgLen];
    System.arraycopy(api_type,0,packet,0,4);
    System.arraycopy(total_len,0,packet,4,4);
    System.arraycopy(msg_len,0,packet,8,4);
    System.arraycopy(msgBytes,0,packet,12,msgLen);
    
    
    out.write(packet);
}


}
