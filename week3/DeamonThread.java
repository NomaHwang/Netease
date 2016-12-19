import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class DeamonThreadFac implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
	
}
public class DeamonThread implements Runnable{
	
	private int id;
	
	public DeamonThread(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	@Override
	public void run(){
		while(true){
			System.out.println("deamon task" + id);
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			}
			catch(Exception e){
				System.out.print("deamon iterrupted");
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		Executor exe = Executors.newCachedThreadPool(new DeamonThreadFac());
		for(int i = 0; i < 5;  ++i){
			exe.execute(new DeamonThread(i));
		}
		
		System.out.println("All deamon started");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("All deamon stopped");
	}
}


