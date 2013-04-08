package pattern.factory.calculator;

public class OperationFactory {
	public static Operation createOperation(char operate) {
		Operation oper = null;
		switch (operate) {
		case '+':
			oper = new OperationAdd();
			break;

		default:
			break;
		}
		return oper;
	}
}
