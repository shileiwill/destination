package company.amazon;
/**
 * In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under behavior pattern.
In Strategy pattern, we create objects which represent various strategies and a context object whose behavior varies as 
per its strategy object. The strategy object changes the executing algorithm of the context object.
城市到达： 如果client有很多特殊请求(如不想经过城市A,不想途转太多城
市)怎么办?答:Strategy Design Pattern
 */
public class StrategyPattern {

	public static void main(String[] args) {
		Context context = new Context(new AddStrategy());
		System.out.println(context.executeStrategy(4, 5));
	
		context = new Context(new MinusStrategy());
		System.out.println(context.executeStrategy(4, 5));
	}

}

interface Strategy {
	int doOperation(int num1, int num2);
}

class AddStrategy implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}

class MinusStrategy implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}

class Context {
	Strategy strategy;
	
	Context(Strategy strategy) {
		this.strategy = strategy;
	}
	
	int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}