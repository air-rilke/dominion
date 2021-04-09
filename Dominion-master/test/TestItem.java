package test;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class TestItem implements Callable<TestResult> {
	private Test test;
	private Consumer<Test> test_function;

	public TestItem(Test t, Consumer<Test> test_function) {
		this.test = t;
		this.test_function = test_function;
	}

	public TestResult call() {
		try {
			this.test_function.accept(this.test);
			if (this.test.isTestOk()) {
				return TestResult.PASS;
			} else {
				return TestResult.FAIL;
			}
		} catch (Exception e) {
			// exception levée
			return TestResult.ERROR;
		}
	}
}
