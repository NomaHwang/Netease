
public class FactoryTest {
	public static void main(String[] args){
		IFactory operFac = new AddFactory();
		double a = 1.238;
		double b = 3.4566;
		Operation op = operFac.createOperation(a, b);
		double res = op.getResult();
		
		System.out.println(res);
		
	}
}
