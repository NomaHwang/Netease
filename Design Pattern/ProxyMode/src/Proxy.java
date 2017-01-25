
public class Proxy extends Subject{
	private RealSubject rsub;

	@Override
	public void reguest() {
		// TODO Auto-generated method stub
		if(rsub == null){
			rsub = new RealSubject();
		}
		rsub.reguest();
	}
	
}
