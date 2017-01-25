import java.util.ArrayList;
import java.util.Iterator;

public abstract class Subject {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void add(Observer o){
		observers.add(o);
	}
	
	public void delete(Observer o){
		Iterator<Observer> iter = observers.iterator();
		while(iter.hasNext()){
			Observer each = iter.next();
			if(each.equals(o)){
				iter.remove();
			}
		}
	}
	
	public void notifyAllObersers(){
		for(Observer obs: observers){
			obs.update();
		}
	}
}
