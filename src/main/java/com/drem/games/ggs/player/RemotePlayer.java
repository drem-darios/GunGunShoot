package com.drem.games.ggs.player;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author drem
 */
public class RemotePlayer extends Player {
    protected OutputStreamWriter output;
    protected InputStreamReader input;
    private Socket sock;
    
    public RemotePlayer(Socket socket) throws IOException
    {
		this.sock = socket;
        openStream();
    }
   
    public boolean isConnected() {
    	return sock.isConnected();
    }
    public void write(String message) throws IOException
    {
        openStream();
        output.write(message);
        output.flush();
    }
    
    public String read() throws IOException
    {
        int data = -1;
        StringBuffer reply = new StringBuffer();
        
        while(true)
        {
            data = input.read();
            //connection was closed
            if(data == -1)
            {
               break; 
            }
            reply.append((char)data);
            
            //reply is at least 2 chars long
            if (reply.length() > 1)
            {
                //check if this and previous char are 59 (";;")
                if(reply.codePointAt(reply.length() - 2) == ';' 
                        && data == ';')
                {
                    break;
                }
            }
        }
        
        return reply.toString();
    }
    
    /**
     * Closes Input/Output buffer streams
     * @throws IOException
     */
    public void closeStream() throws IOException
    {
        output.close();
        input.close();
        sock.close();
    }
    
    /**
     * Opens Input/Output buffer streams using utf-8 encoding
     * @throws IOException
     */
    public void openStream() throws IOException
    {
        OutputStream rawOut = new BufferedOutputStream(sock.getOutputStream());
        InputStream rawIn = new BufferedInputStream(sock.getInputStream());
        
        output = new OutputStreamWriter(rawOut, "UTF-8");
        input = new InputStreamReader(rawIn, "UTF-8");
        
        output.flush();
    }
	
}
