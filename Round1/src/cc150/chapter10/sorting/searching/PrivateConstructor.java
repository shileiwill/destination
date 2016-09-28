package cc150.chapter10.sorting.searching;

public class PrivateConstructor {

}

class Test{
	private Test() {
		
	}
	Test(int a) {
		
	}
	
	class TestInnerChild {
		// This inner class doesnt matter
	}
}

class TestChild extends Test{
	TestChild(int a) {
		super(a);// If not this, it will by default call super(), which is unvisible
	}
}