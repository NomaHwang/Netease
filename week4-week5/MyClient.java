import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Chat implements Runnable{
	private Socket s;
	
	public Chat(Socket s){
		this.s = s;
	}
	
	public void run(){
		
		BufferedReader sockin;
		try {
			sockin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedReader kin = new BufferedReader(new InputStreamReader(System.in));
			
			PrintStream out = new PrintStream(s.getOutputStream());
			
			while(true){
				System.out.println("input your message:");
				String str = kin.readLine();
				out.print(str);
				
				if(str.equalsIgnoreCase("bye") || str.equalsIgnoreCase("quit")){
					System.out.println("shut down connection...");
					System.exit(0);
				}
				
				try{
					String serin = sockin.readLine();
					System.out.println("from server: " + serin);
				}
				catch(SocketException e){
					e.printStackTrace();
					return;
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}		
	
	}
}

class HeartBeat implements Runnable{
	private Socket s;	
	
	public HeartBeat(Socket s, ExecutorService exe){
		this.s = s;
	
	}
	
	public void run(){
		while(true){
			try {
				s.sendUrgentData(0x0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("lost connection with server!");
				System.exit(0);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}	

}

public class MyClient {
	private String hostname = "127.0.0.1";
	private int port = 5000;
	private int timeout = 5000;
	private Socket client;
	
	public MyClient(){
		start();
	}
	
	public MyClient(String hostname, int port, int timeout){
		this.hostname = hostname;
		this.port = port;			
		start();
	}
	
	
	public void start(){
		client = new Socket();
		try {
			client.connect(new InetSocketAddress(hostname, port));
			ExecutorService exe = Executors.newCachedThreadPool();
			exe.execute(new Chat(client));
			HeartBeat h = new HeartBeat(client, exe);
			exe.execute(h);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		MyClient noma = new MyClient();		
	}
}
