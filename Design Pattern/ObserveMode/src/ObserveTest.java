
public class ObserveTest {
	public static void main(String[] args){
		SubjectA a = new SubjectA();
		a.add(new ObserverA());
		a.add(new ObserverB());
		
		a.setState(true);
		a.notifyAllObersers();
	}
}
