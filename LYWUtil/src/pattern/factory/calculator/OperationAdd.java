package pattern.factory.calculator;

public class OperationAdd extends Operation {

	@Override
	public double getResult() {
		double result = 0;
		result = super.getNumA() + super.getNumB();
		// TODO Auto-generated method stub
		return result;
	}
	
}
