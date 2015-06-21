package mhewedy.fsm;

import java.lang.reflect.Field;

public class ProxyObject<F> {
	F o;

	/**
	 * Never use this constructor directly, use {@code Manager#of(Object)} instead.
	 * @param o
	 */
	ProxyObject(F o) {
		this.o = o;
	}

	@SuppressWarnings("unchecked")
	public <E> E get(String fieldName) {
		return (E) readField(o, fieldName);
	}

	/**
	 * 
	 * @return reference to the wrapped object
	 */
	public F get() {
		return o;
	}

	private Object readField(Object o, String fieldName) {
		try {
			Field f = o.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			return f.get(o);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}