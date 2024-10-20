/*
 * Parker Zhang
 * Turner pd 7
 * Class that is a server of threads that accept when the client joins and tracks the attempts of each client/ip
 */


import java.util.*;
import java.net.*;
import java.io.*;

public class ParkerZhangThreadedServer {

	private ArrayList<String> bannedIPs;
	private HashMap<String,Integer> attempts;
	private final String PASSWORD = "park";
	
	public ParkerZhangThreadedServer() {

		bannedIPs = new ArrayList<String>();
		attempts = new HashMap<String,Integer>();
		
		try {
			
			System.out.println("Server: ");
			ServerSocket server = new ServerSocket(4000);
			System.out.println(server.getLocalPort());
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			
			while(true) {
				Socket theSock = server.accept();
				Thread clientThread = new Thread(new ClientHandler(theSock));
				clientThread.start();
				
				
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public class ClientHandler implements Runnable{
		
		private String ip;
		private String port;
		private Scanner in;
		private PrintWriter out;
		
		public ClientHandler(Socket theSock) {
			
			//get ip of specific thread
			String temp = theSock.getRemoteSocketAddress().toString();
			
			temp = temp.substring(1);
			
			ip = temp.substring(0,temp.indexOf(":"));
			
			port = temp.substring(temp.indexOf(":")+1,temp.length());
			
			try {
				
				out = new PrintWriter(theSock.getOutputStream());
			
				//check if the specific ip is already banned
				if(bannedIPs.indexOf(ip)==-1) {
					out.println("NO");
					out.flush();
				}
				
				else {
					out.println("Connected to: " + ip + " - Port #: " + port);
					out.flush();
				}
				
				if(!attempts.containsKey(ip)) 
						attempts.put(ip, 3);
				
				in = new Scanner(theSock.getInputStream());
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			System.out.println(port);
			
			
			//use theSock to set up scanner/printwriter
			//scanner/printwriter can be innerclass IVs
		}
		
		public void run() {

			int curAttempts = 3;
			
			//track the attempts of returning clients
			if(attempts.containsKey(ip))
				curAttempts = attempts.get(ip);
			
			while(in.hasNextLine()) {
				
				if(curAttempts == 0) {
					out.println("NO");
					out.flush();
					
					synchronized(bannedIPs) {
						bannedIPs.add(ip);
					}
					
				}
				else {
					
					String input = in.nextLine();
				
					if(input.equals(PASSWORD)) {
						out.println("ACCEPTED");
						out.flush();
						
						System.out.println("accepted");
					}
					
					else {
						out.println(input + " is Incorrect");
						out.flush();
						
						curAttempts--;
					}
					
					
				}
				
				
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		
		ParkerZhangThreadedServer server = new ParkerZhangThreadedServer();
	}
}
