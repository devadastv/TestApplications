package com.devadas.poc.java8;

public class DefaultMethodTest implements Int1, Int2 {

	@Override public void int2Method() {}
	@Override public void int1Method() {}
	@Override public void commonMethod() {}
	@Override public void log() {
		System.out.println();
	}

}

interface Int1 {
	void int1Method();
	void commonMethod();
	default void log() {
		System.out.println("Log from Int1");
	}
	static boolean isNull(Int1 obj) { return obj == null;}
}

interface Int2 {
	void int2Method();
	void commonMethod();
	default void log() {
		System.out.println("Log from Int2");
	}
}

@FunctionalInterface
interface Int3 {
	void int3Method();
	default void log() {
		System.out.println("Log from Int3");
	}
	static boolean isNull(Int1 obj) { return obj == null;}
}