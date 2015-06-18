package mhewedy.fsm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhewedy.fsm.holders.InStatusHolder;

@SuppressWarnings("unchecked")
public class Manager {

	private static final Map<Object, ProxyObject<?>> proxyObjectMap = new HashMap<>();
	private static final Map<List<?>, ProxyIterable<?>> proxyIterMap = new HashMap<>();

	public static <T> ProxyObject<T> of(T o) {
		if (!proxyObjectMap.containsKey(o)) {
			proxyObjectMap.put(o, new ProxyObject<T>(o));
		}
		return (ProxyObject<T>) proxyObjectMap.get(o);
	}

	public static <T> ProxyIterable<T> of(List<T> list) {
		if (!proxyIterMap.containsKey(list)) {
			proxyIterMap.put(list, new ProxyIterable<T>(list));
		}
		return (ProxyIterable<T>) proxyIterMap.get(list);
	}

	public static <T> Predicate<T> equal(T t) {
		return new Predicate.EqualPredicate<T>(t);
	}

	public static <T extends Comparable<T>> Predicate<T> greater(T t) {
		return new Predicate.GreaterPredicate<T>(t);
	}

	public static <T extends Comparable<T>> Predicate<T> less(T t) {
		return new Predicate.LessPredicate<T>(t);
	}

	public static <E extends Enum<?>> InStatusHolder<E> from(E currentStatus) {
		InStatusHolder<E> holder = new InStatusHolder<>();
		holder.setInput(currentStatus);
		return holder;
	}

	public static OrCondition or(boolean test) {
		OrCondition orCondition = new OrCondition();
		orCondition.boolList.add(test);
		return orCondition;
	}

}
