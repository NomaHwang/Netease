
public class AddOperation implements Operation{
	
	private double operateA = 0.0;
	private double operateB = 0.0;
	
	public AddOperation(){}
	
	public  AddOperation(double a, double b) {
		// TODO Auto-generated constructor stub
		operateA = a;
		operateB = b;
	}
	
	@Override
	public double getResult() {
		// TODO Auto-generated method stub
		double res = operateA + operateB;
		return res;
	}

}
