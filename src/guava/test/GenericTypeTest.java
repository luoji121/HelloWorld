package guava.test;

public class GenericTypeTest<T> {
	public static <T> GenericTypeTest<T> doTest(T t){
		return new GenericTypeTest<T>();
	}
	public static void main(String[] args) {
		GenericTypeTest.<String>doTest("aaa");
	}

}
