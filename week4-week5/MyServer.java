import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class HandleConnect implements Runnable{
	private Socket s;
	private int id;
	
	
	public HandleConnect(Socket s, int id) {
		this.s = s;
		this.id = id;		
	}
	
	@Override
	public void run() {		
		System.out.println("have a new client");
		echo();
	}	
	
	public void echo(){
			
		try {					
			Reader reader = new InputStreamReader(s.getInputStream());		
			PrintStream out = new PrintStream(s.getOutputStream());
			
			int bufferSize = 256;
			char[] buffer = new char[bufferSize];
				
			StringBuilder sb = new StringBuilder();	
			while(true){					
				while(true){
					try{
						int n = reader.read(buffer);
						if(n < 0){							
							return;
						}
						sb.append(buffer, 0, n);
						if(n < bufferSize){
							break;
						}
					}
					catch(SocketException e){
						return;
					}					
				}				
				if(sb.length() > 0){
					System.out.println("from clinet " + id + ": " + sb);				
					out.print("echo:" + sb.toString() + "\n");
					
					if(sb.toString().compareTo("bye") == 0 || sb.toString().compareTo("exit") == 0){					
						break;
					}	
					sb.delete(0, sb.length());
				}			
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();		
			return;
		}
	}
	
	public void sendFile(){
		SocketChannel sc = s.getChannel();
		String filename = "/files/new.txt";
		int capacity = 4096;
		try {
			FileInputStream fin = new FileInputStream(filename);
			ByteBuffer buffer = ByteBuffer.allocate(capacity);
			buffer.flip();
			sc.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}

public class MyServer {
	private int port = 5000;
	private int backlog = 5;
	private int timeout = 1000 * 60 * 5;
	
	private ServerSocket ss;	
	
	public MyServer(){
		start();
	}		
	
	public MyServer(final int port, final int backlog) {
		this.port = port;
		this.backlog = backlog;
		start();
	}
	
	private  void start(){
		try {
			ss = new ServerSocket(port, backlog);			
			int id = 0;		
			System.out.println("Start to work");
			Thread t = new Thread(new Runnable(){
				public void run(){					
					ExecutorService exe = Executors.newCachedThreadPool(new ThreadFactory() {
						
						@Override
						public Thread newThread(Runnable r) {
							// TODO Auto-generated method stub
							Thread t = new Thread(r);
							t.setDaemon(true);
							return t;
						}
					});
					
					int id = 0;
					while(true){
						
						try {
							Socket s = ss.accept();							
							exe.execute(new HandleConnect(s, id));
							++id;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
			
			t.setDaemon(true);
			t.start();
			
			InputStreamReader input = new InputStreamReader(System.in);
			char[] cbuf = new char[200];
			try {
				if(input.read(cbuf) > 0){
					System.exit(0);					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	
	public static void main(String[] args){		
		new MyServer();
	}
}
