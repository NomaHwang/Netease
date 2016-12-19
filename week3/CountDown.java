import java.util.ArrayList;
import java.util.concurrent.*;

public class CountDown implements Runnable{
	private int num = 10;
	private static int taskId = 0;
	private final int id = taskId++;
	
	public CountDown(int num) {
		// TODO Auto-generated constructor stub
		this.num = num;
	}

	public String getState(){
		return Thread.currentThread() + "#" + id + "(" + (num > 0 ? num : "Happy New Year!") + "), ";
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(num-- > 0){
			System.out.println(getState());
			try {
				//Thread.sleep(100);
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Thread.yield();
	}
	
	public static void main(String[] args){
		ExecutorService exc = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; ++i){
			exc.execute(new CountDown(10));
		}
		exc.shutdown();
		
	}

}

