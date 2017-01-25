
public class SubjectA extends Subject{
	private boolean state = false;
	
	public boolean getSate(){
		return state;
	}
	
	public void setState(boolean s){
		state = s;
	}
	
	public void notifyAllObersers(){
		if(state == true){
			super.notifyAllObersers();
		}
	}
}
