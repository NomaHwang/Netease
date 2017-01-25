
public class AddFactory implements IFactory {

	@Override
	public Operation createOperation(double a, double b) {
		// TODO Auto-generated method stub
		return new AddOperation(a, b);
	}
}
